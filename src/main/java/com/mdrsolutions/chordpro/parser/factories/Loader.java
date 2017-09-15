package com.mdrsolutions.chordpro.parser.factories;

/**
 * Interface is designed to loosely describe the specification for how songs are
 * loaded (SR), Produced(P), and Presented(SC). The Song Loading (SR) could be
 * through a File, String, Stream, DAOfactory, or WebService. The idea here is
 * allow this interface to be expanded to meet ideas that may come in the future
 * to loading a lyric.
 *
 * @author mrodgers
 * @param <SC> Song Line Collection
 * @param <SR> Song File, String, or some retrieving mechanism
 * @param <P> Song Line Producer
 */
public interface Loader<SC, SR, P> {

    SC load(SR song);
}
