package searchengine.element;

public class PageAlt implements PageElementInterface {

	public PageAlt (String w) {
		alt = w;
	}

	public String toString () {
		return alt;
	}

	private String alt;
}
