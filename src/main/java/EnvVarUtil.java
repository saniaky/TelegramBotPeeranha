import java.util.Map;

public class EnvVarUtil {

    public static String getVar(String key) {
        Map<String, String> map = System.getenv();
        return map.get(key);
    }

}
