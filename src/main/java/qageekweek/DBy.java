package qageekweek;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public abstract class DBy extends By {

	/**
	 * @param id The value of the "id" attribute to search for.
	 * @return A By which locates elements by the value of the "id" attribute.
	 */
	public static By id(String id, String description) {
		return new DById(id, description);
	}

	/**
	 * @param linkText The exact text to match against.
	 * @return A By which locates A elements by the exact text it displays.
	 */
	public static By linkText(String linkText, String description) {
		return new DByLinkText(linkText, description);
	}

	/**
	 * @param partialLinkText The partial text to match against
	 * @return a By which locates elements that contain the given link text.
	 */
	public static By partialLinkText(String partialLinkText, String description) {
		return new DByPartialLinkText(partialLinkText, description);
	}

	/**
	 * @param name The value of the "name" attribute to search for.
	 * @return A By which locates elements by the value of the "name" attribute.
	 */
	public static By name(String name, String description) {
		return new DByName(name,description);
	}

	/**
	 * @param tagName The element's tag name.
	 * @return A By which locates elements by their tag name.
	 */
	public static By tagName(String tagName, String description) {
		return new DByTagName(tagName,description);
	}

	/**
	 * @param xpathExpression The XPath to use.
	 * @return A By which locates elements via XPath.
	 */
	public static By xpath(String xpathExpression, String description) {
		return new DByXPath(xpathExpression,description);
	}

	/**
	 * Find elements based on the value of the "class" attribute. If an element has
	 * multiple classes, then this will match against each of them. For example, if
	 * the value is "one two onone", then the class names "one" and "two" will
	 * match.
	 *
	 * @param className The value of the "class" attribute to search for.
	 * @return A By which locates elements by the value of the "class" attribute.
	 */
	public static By className(String className, String description) {
		return new DByClassName(className,description);
	}

	/**
	 * Find elements via the driver's underlying W3C Selector engine. If the browser
	 * does not implement the Selector API, a best effort is made to emulate the
	 * API. In this case, we strive for at least CSS2 support, but offer no
	 * guarantees.
	 *
	 * @param cssSelector CSS expression.
	 * @return A By which locates elements by CSS.
	 */
	public static By cssSelector(String cssSelector, String description) {
		return new DByCssSelector(cssSelector,description);
	}

	/**
	 * Find a single element. Override this method if necessary.
	 *
	 * @param context A context to use to find the element.
	 * @return The WebElement that matches the selector.
	 */
	public WebElement findElement(SearchContext context) {
		List<WebElement> allElements = findElements(context);
		if (allElements == null || allElements.isEmpty()) {
			throw new NoSuchElementException("Cannot locate an element using " + toString());
		}
		return allElements.get(0);
	}

	@Override
	public List<WebElement> findElements(SearchContext context) {
		return null;
	}

	public static class DById extends ById implements Serializable, DescriptiveBy {

		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DById(String id, String description) {
			super(id);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}


	}

	public static class DByLinkText extends ByLinkText implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DByLinkText(String linkText, String description) {
			super(linkText);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}


	}

	public static class DByPartialLinkText extends ByPartialLinkText implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DByPartialLinkText(String partialLinkText, String description) {
			super(partialLinkText);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}


	}

	public static class DByName extends ByName implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String description;
		
		public DByName(String name, String description) {
			super(name);
			this.description = description;
		}
		@Override
		public String getDescription() {
			return description;
		}

	}

	public static class DByTagName extends ByTagName implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;

		public DByTagName(String tagName, String description) {
			super(tagName);
			this.description = description;
		}

		@Override
		public String getDescription() {
			return description;
		}
	}

	public static class DByXPath extends ByXPath implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DByXPath(String xpathExpression, String description) {
			super(xpathExpression);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}

	}

	public static class DByClassName extends ByClassName implements Serializable, DescriptiveBy {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DByClassName(String className, String description) {
			super(className);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}

	}

	public static class DByCssSelector extends ByCssSelector implements Serializable, DescriptiveBy {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String description;
		
		public DByCssSelector(String cssSelector, String description) {
			super(cssSelector);
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return description;
		}


	}

}