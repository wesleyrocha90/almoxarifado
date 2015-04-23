package br.com.rocha.almoxarifado.util;

import java.util.HashMap;
import java.util.Map;

public class Maps {

  public static <K, V> Map<K, V> asMap(Object... keyvals) {
    return fillMap(new HashMap<>(keyvals.length), keyvals);
  }

  public static <K, V, T extends Map<K, V>> T asMap(T map, Object... keyvals) {
    return fillMap(map, keyvals);
  }

  private static <K, V, T extends Map<K, V>> T fillMap(T map, Object... keyvals) {
    if (keyvals.length > 0) {
      if (keyvals.length % 2 == 0) {
        for (int i = 0; i < keyvals.length; i += 2) {
          map.put((K) keyvals[i], (V) keyvals[i + 1]);
        }
      }
    }
    return map;
  }
}
