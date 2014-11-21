package jannovar;

import htsjdk.variant.variantcontext.VariantContext;
import jannovar.exception.AnnotationException;

import java.io.IOException;

/**
 * Interface for output writers in Jannovar class.
 *
 * The task of such a writer is to take a HTSJDK annotation, perform annotation with the Jannovar code and then write it
 * out into some output format.
 *
 * Currently, we have to convert HTSJDK VariantContext objects into Jannovar Variant objects but that should be
 * simplified later on.
 *
 * @author Manuel Holtgrewe <manuel.holtgrewe@charite.de>
 */
// TODO(holtgrew): Update comment above once we use VariantContext everywhere.
public abstract class AnnotatedVariantWriter {
	/**
	 * Write out the given VariantContext with additional annotation.
	 *
	 * @throws AnnotationException
	 *             when a problem with annotation occurs
	 * @throws IOException
	 *             when problem with I/O occurs
	 */
	public abstract void put(VariantContext vc) throws AnnotationException, IOException;

	/** Returns output path */
	public abstract String getOutFileName();

	/** Close writer, free resources */
	abstract void close();

	/**
	 * Temporary helper code for converting VCF data to Jannovar representation.
	 */
	// TODO(holtgrem): remove this class eventually once using HTSJDK VariantContext everywhere.
	protected class VariantDataCorrector {
		protected String ref;
		protected String alt;
		protected int position;

		public VariantDataCorrector(String ref, String alt, int position) {
			this.ref = ref;
			this.alt = alt;
			this.position = position;

			correct();
		}

		private void correct() {
			int idx = 0;
			// beginning
			while (idx < ref.length() && idx < alt.length() && ref.charAt(idx) == alt.charAt(idx)) {
				idx++;
			}
			position += idx;
			ref = ref.substring(idx);
			alt = alt.substring(idx);

			// end
			int xdi = ref.length();
			int diff = ref.length() - alt.length();
			while (xdi > 0 && xdi - diff > 0 && ref.charAt(xdi - 1) == alt.charAt(xdi - 1 - diff)) {
				xdi--;
			}
			ref = xdi == 0 ? "-" : ref.substring(0, xdi);
			alt = xdi - diff == 0 ? "-" : alt.substring(0, xdi - diff);
		}
	}
}