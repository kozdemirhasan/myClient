package de.kozdemir.client.model;

import java.util.List;


@FunctionalInterface
interface SearchProducts<T> {
	List<T> search(String s);
}
