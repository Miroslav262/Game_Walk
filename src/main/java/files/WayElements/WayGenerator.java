package files.WayElements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class WayGenerator{
    public static List<WayElement> genWay(InputStream is)
            throws IOException,
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode root = mapper.readTree(is);

        Random random = new Random();
        int n = random.nextInt(
                root.get("mapSize").get("min").asInt(),
                root.get("mapSize").get("max").asInt()
        );

        List<WayElement> result = new ArrayList<>();
        Iterator<String> values = root.get("wayProbabilities").fieldNames();

        while (values.hasNext()) {
            String str = values.next();
            Class<?> obj = Class.forName("files.WayElements." + str);
            double probability = root.get("wayProbabilities").get(str).asDouble();

            int localN = (int) Math.round(probability * n);
            for (int i = 0; i < localN; i++) {
                WayElement element = (WayElement) obj.getDeclaredConstructor().newInstance();
                result.add(element);
            }
        }

        Collections.shuffle(result);
        result.addFirst(new Start());
        result.addLast(new Finish());

        return result;
    }

}
