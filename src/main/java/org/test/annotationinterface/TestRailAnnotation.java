package org.test.annotationinterface;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Annotation interface to create custom annotations to display these values in TestRail.
 * May 16, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface TestRailAnnotation {
		String[] author() default "Mandeep Sheoran";
		String id() default "none";
	}

