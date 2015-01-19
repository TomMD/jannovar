/**
 *
 */
package de.charite.compbio.jannovar.impl.parse.gff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.charite.compbio.jannovar.impl.parse.InvalidAttributeException;
import de.charite.compbio.jannovar.impl.parse.gff.FeatureProcessor.Gene;
import de.charite.compbio.jannovar.impl.parse.gff.FeatureProcessor.Transcript;
import de.charite.compbio.jannovar.io.ReferenceDictionary;
import de.charite.compbio.jannovar.reference.GenomeInterval;
import de.charite.compbio.jannovar.reference.PositionType;
import de.charite.compbio.jannovar.reference.TranscriptModelBuilder;

/**
 * This is the builder for the {@link TranscriptInfo}s from GFF files.
 *
 * @author Marten Jaeger <marten.jaeger@charite.de>
 * @author Manuel Holtgrewe <manuel.holtgrewe@charite.de>
 */
public final class TranscriptInfoFactory {

	/** {@link Logger} to use for logging */
	private static final Logger LOGGER = Logger.getLogger(TranscriptInfoFactory.class.getSimpleName());

	/** {@link GFFVersion} to assume for building transcripts from Feature objects */
	private final GFFVersion gffVersion;

	/** reference dictionary to use */
	private final ReferenceDictionary refDict;

	public TranscriptInfoFactory(GFFVersion gffVersion, ReferenceDictionary refDict) {
		this.gffVersion = gffVersion;
		this.refDict = refDict;
	}

	/**
	 * Forward to {@link #buildTranscripts(HashMap, boolean)}, setting the second parameter to <code>false</code>.
	 */
	public ArrayList<TranscriptModelBuilder> buildTranscripts(HashMap<String, Gene> genes)
			throws InvalidAttributeException {
		return buildTranscripts(genes, false);
	}

	/**
	 * Process the <code>genes</code> and convert into an {@link ArrayList} of {@link TranscriptInfoBuilder}s.
	 *
	 * @param genes
	 *            the name/Gene map to build the {@link TranscriptInfo} objects for.
	 * @param useOnlyCurated
	 *            whether or not to only return curated transcripts
	 * @return list of {@link TranscriptInfoBuilder} objects
	 * @throws InvalidAttributeException
	 *             on problems with invalid attributes
	 */
	public ArrayList<TranscriptModelBuilder> buildTranscripts(HashMap<String, Gene> genes, boolean useOnlyCurated)
			throws InvalidAttributeException {
		ArrayList<TranscriptModelBuilder> models = new ArrayList<TranscriptModelBuilder>();
		int curid;
		for (FeatureProcessor.Gene gene : genes.values()) {
			if (gene.id == null)
				continue;
			for (Transcript rna : gene.rnas.values()) {
				if (useOnlyCurated && !isCuratedName(rna.name))
					continue;

				TranscriptModelBuilder tib = new TranscriptModelBuilder();
				tib.setAccession(rna.name);
				tib.setGeneSymbol(gene.name);
				tib.setStrand(rna.strand ? '+' : '-');
				tib.setTxRegion(new GenomeInterval(refDict, '+', rna.chromosom, rna.getTxStart(), rna.getTxEnd(),
						PositionType.ONE_BASED));

				// Check whether the corrected CDS start position returned from getCdsStart() is within an exon and do
				// the same for the CDS end position. The correction in these methods can lead to inconsistent positions
				// in the case of 3' and 5' UTR truncation.
				boolean cdsStartInExon = false;
				int cdsStart = rna.getCdsStart();
				for (int i = 0; i < rna.getExonStarts().length; ++i)
					cdsStartInExon = cdsStartInExon
					|| (cdsStart >= rna.getExonStarts()[i] && cdsStart <= rna.getExonEnds()[i]);
				boolean cdsEndInExon = false;
				int cdsEnd = rna.getCdsEnd();
				for (int i = 0; i < rna.getExonStarts().length; ++i)
					cdsEndInExon = cdsEndInExon || (cdsEnd >= rna.getExonStarts()[i] && cdsEnd <= rna.getExonEnds()[i]);
				if (!cdsStartInExon || !cdsEndInExon) {
					LOGGER.log(Level.WARNING, "Transcript {} appears to be 3'/5' truncated. Ignoring.", rna.id);
					continue;
				}
				tib.setCdsRegion(new GenomeInterval(refDict, '+', rna.chromosom, rna.getCdsStart(), rna.getCdsEnd(),
						PositionType.ONE_BASED));

				for (int i = 0; i < rna.exons.size(); ++i)
					tib.addExonRegion(new GenomeInterval(refDict, '+', rna.chromosom, rna.getExonStarts()[i], rna
							.getExonEnds()[i], PositionType.ONE_BASED));

				if (gffVersion.version == 3)
					tib.setGeneID(Integer.parseInt(gene.id.substring(4)));
				else if ((curid = RNA2GeneIDMapper.getGeneID(gene.id)) > 0)
					tib.setGeneID(curid);
				else
					throw new InvalidAttributeException("Found no valid geneID mapping for accession: " + gene.id);

				models.add(tib);
			}
		}

		return models;
	}

	/**
	 * Checks whether <code>rnaName</code> is the name of a curated transcript.
	 *
	 * We consider a name being non-curated if it is <code>null</code> or begins with <code>"XM_"</code>, or
	 * <code>"XR_"</code>. In all other cases, we consider a RNA name as indicating a curated entry. This only works for
	 * RefSeq names.
	 *
	 * @param rnaName
	 *            RNA name to check for matching the "is curated" pattern
	 * @return <code>true</code> if the RNA name is curated.
	 */
	private static boolean isCuratedName(String rnaName) {
		return !((rnaName == null) || rnaName.startsWith("XM_") || rnaName.startsWith("XR_"));
	}

}