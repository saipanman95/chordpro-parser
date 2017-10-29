package com.mdrsolutions.chordpro.parser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Class used by Aws for calling SimpleTextSongParser.
 * AwsSimpleTextSongParser.handleRequest accepts Base64 endcoded copy of songpro
 * formatted song and returns Base64 encoded String.
 *
 * @author mrodgers
 */
public class AwsSimpleTextSongParser implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String request, Context context) {

        return new SimpleTextSongParser().parseBase64(request);
    }
}
