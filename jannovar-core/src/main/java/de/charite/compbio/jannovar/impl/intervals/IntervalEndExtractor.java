package de.charite.compbio.jannovar.impl.intervals;

/**
 * Allows extraction of begin and end position for a type.
 *
 * @param <T> the type to allow extraction of
 * @author <a href="mailto:manuel.holtgrewe@charite.de">Manuel Holtgrewe</a>
 */
public interface IntervalEndExtractor<T> {

	/**
	 * @return begin position of <code>x</code> (inclusive)
	 */
	public int getBegin(T x);

	/**
	 * @return begin position of <code>x</code> (exclusive)
	 */
	public int getEnd(T x);

}
