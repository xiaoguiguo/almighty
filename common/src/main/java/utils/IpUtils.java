package utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Slf4j
public class IpUtils {
    public static final String UNKNOWN = "unknown";

    private IpUtils() {
    }

    public static String get(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = UNKNOWN;
            }
        }
        if (UNKNOWN.equalsIgnoreCase(ip)) {
            ip = UNKNOWN;
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0) {
            ip = ip.substring(0, index);
        }
        return ip;

    }



    public static String index(String domain) {
        return "https://" + domain;
    }

    @SneakyThrows
    public static String localAddress(){
        return localInetAddress().getHostAddress();
    }
    /**
     * 获取机器的网络地址
     */
    private static InetAddress localInetAddress() {
        try {
            final InetAddress host = InetAddress.getLocalHost();
            if (!host.isLoopbackAddress()) {
                final byte[] mac = NetworkInterface.getByInetAddress(host).getHardwareAddress();
                if (mac != null) { // 可能拿到VPN的虚拟网卡，虚拟网卡无法获取MAC地址
                    return host;
                }
            }
        } catch (Exception e) {
            log.warn("Error at InetAddress.getLocalHost(), try to read NetworkInterface");
        }
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp() || networkInterface.isPointToPoint()) {
                    continue;
                }

                List<InetAddress> addresses = Collections.list(networkInterface.getInetAddresses());
                final Optional<InetAddress> target = addresses.stream().filter(Inet4Address.class::isInstance).findFirst();
                if (target.isPresent()) {
                    return target.get();
                }
            }
        } catch (Exception e) {
            log.error("Error when getting InetAddress.", e);
        }
        throw  new IllegalStateException("local host get error");
    }
}
