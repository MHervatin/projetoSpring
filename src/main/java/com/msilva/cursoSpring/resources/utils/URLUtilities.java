package com.msilva.cursoSpring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para manipulação de URL.
 *
 * @author Mateus
 */
public class URLUtilities {

    /**
     * Converte uma string de ids separada por vírgula em uma lista de Long.
     *
     * @param ids A String de ids.
     *
     * @return Uma lista de Long contendo os ids da String.
     */
    public static List<Long> converteStringEmListaLong(String ids) {
        return Arrays.asList(ids.split(",")).stream()
                .map(id -> Long.parseLong(id))
                .collect(Collectors.toList());
    }

    /**
     * Decodifica o parâmetro informado em 'UTF-8'.
     *
     * @param parametro a ser decodificado.
     *
     * @return O parâmetro decodificado.
     */
    public static String decodificarParametroString(String parametro) {
        try {
            return URLDecoder.decode(parametro, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
}
