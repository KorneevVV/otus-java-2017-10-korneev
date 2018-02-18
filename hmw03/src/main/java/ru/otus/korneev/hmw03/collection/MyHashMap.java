package ru.otus.korneev.hmw03.collection;

public class MyHashMap<K, V> {

	private int arrayLength = 16;

	@SuppressWarnings("unchecked")
	private Node<K, V>[] array = new Node[arrayLength];

	private static final float LOAD_FACTOR = 0.75f;

	private int size = 0;

	public V add(K key, V value) {
		if (key == null) {
			return addNullKey(value);
		}
		int hash = calculateHash(key);
		if (size >= LOAD_FACTOR * array.length) {
			reSize();
		}
		int index = calculateIndex(hash);
		Node<K, V> firstNode = array[index];
		if (firstNode == null) {
			array[index] = new Node<>(null, key, value, hash);
			size++;
			return null;
		} else {
			for (Node<K, V> node = array[index]; node != null; node = node.next) {
				if (node.key.equals(key) && node.hash == hash) {
					V oldValue = array[index].value;
					array[index] = new Node<>(node.next, key, value, hash);
					return oldValue;
				} else if (node.next == null) {
					array[index] = new Node<>(firstNode, key, value, hash);
					size++;
					return null;
				}
			}
		}
		return null;
	}

	private int calculateIndex(int hash) {
		int index = hash % array.length;
		if (index == 0) {
			index++;
		}//array[0] can only store null key
		return index;
	}

	private V addNullKey(V value) {
		if (array[0] != null) {
			V oldValue = array[0].value;
			array[0].value = value;
			return oldValue;
		} else {
			array[0] = new Node<>(null, null, value, 0);
			size++;
		}
		return value;
	}

	private int calculateHash(K key) {
		int hash = key.hashCode();
		if (hash < 0) {
			hash *= -1;
		}
		return hash;
	}

	@SuppressWarnings("unchecked")
	private void reSize() {
		Node[] arrayNew = new Node[array.length * 2];
		for (Node<K, V> node : array) {
			while (node != null) {
				Node<K, V> next = node.next;
				node.hash = node.key == null ? 0 : node.key.hashCode();
				int index = node.hash % arrayNew.length;
				if (index == 0 && node.key != null) {
					index++;
				}
				node.next = arrayNew[index];
				arrayNew[index] = node;
				node = next;
			}
		}
		array = arrayNew;
	}

	public V get(K key) {
		if (key == null && array[0] != null) {
			return array[0].value;
		}
		int hash = calculateHash(key);
		int index = calculateIndex(hash);
		if (array[index] != null) {
			Node<K, V> node = array[index];
			while (node != null) {
				if (node.key.equals(key)) {
					return node.value;
				}
				node = node.next;
			}
		}
		return null;
	}

	public V remove(K key) {
		if (size == 0) {
			return null;
		}
		V removableValue;
		if (key == null && array[0] != null) {
			removableValue = array[0].value;
			array[0] = null;
			size--;
			return removableValue;
		}
		int hash = calculateHash(key);
		int index = calculateIndex(hash);
		if (array[index] != null) {
			Node<K, V> prev = array[index];
			Node<K, V> node = prev;
			while (node != null) {
				Node<K, V> next = node.next;
				if (node.key.equals(key)) {
					removableValue = node.value;
					size--;
					if (node == prev) {
						array[index] = next;
					} else {
						prev.next = next;
					}
					return removableValue;
				}
				prev = node;
				node = next;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (Node<K, V> anArray : array) {
			Node<K, V> node = anArray;
			if (anArray != null) {
				do {
					b.append("key:").append(node.key).append(" value:").append(node.value).append("; ").append('\n');
					node = node.next;
				} while (node != null);
			}
		}
		return b.toString();
	}

	private static class Node<K, V> {
		Node<K, V> next;
		K key;
		V value;
		int hash;

		Node(Node<K, V> next, K key, V value, int hash) {
			this.next = next;
			this.key = key;
			this.value = value;
			this.hash = hash;
		}

		@Override
		public boolean equals(Object externalNode) {
			if (!(externalNode instanceof Node)) {
				return false;
			}
			Node e = (Node) externalNode;
			Object keyExternalNode = e.key;
			if (key.equals(keyExternalNode)) {
				Object valueExternalNode = e.value;
				return value.equals(valueExternalNode);
			}
			return false;
		}
	}
}
