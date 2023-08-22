import java.util.NoSuchElementException;
import java.util.Scanner;

class MyDLinkedList<T> {
	Node<T> head;
	Node<T> tail;
	int size = 0;

	public MyDLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	int size() {
		return size();
	}

	boolean isEmpty() {
		// if (head == null) return true;
		if (size == 0)
			return true;
		return false;
	}

	Node<T> search(int idx) {

		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (idx + 1 > size / 2) {
			Node<T> xNode = tail;
			for (int i = size - 1; i > idx; i--) {
				xNode = xNode.prev;
			}
			return xNode;
		}

		else {
			Node<T> xNode = head;
			for (int i = 0; i < idx; i++) {
				xNode = xNode.next;
			}
			return xNode;
		}

	}

	void add(T data) {
		addLast(data);
	}

	void addFirst(T data) {
		Node<T> newNode = new Node<T>(data);
		newNode.next = head;

		// 빈 링크드리스트가 아니면 head 갱신
		if (head != null) {
			head.prev = newNode;
		}

		head = newNode;
		size++;

		// 원래 빈 링크드리스트였던 경우 더해준 새 노드는 마지막 노드이기도 하다.
		// 아니면 마지막 노드는 변동될 이유가 없다.
		if (head.next == null) {
			tail = head;
		}
	}

	void addLast(T data) {

		// 빈 리스트였던 경우 addFirst
		if (size == 0) {
			addFirst(data);
			return;
		}

		Node<T> newNode = new Node<T>(data);

		// 원래 마지막 노드의 next, 새로 더하는 노드의 prev 갱신
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		size++;
	}

	void add(int idx, T data) {

		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (idx == 0) {
			addFirst(data);
			return;
		}

		if (idx == size) {
			addLast(data);
			return;
		}

		Node<T> newNode = new Node<T>(data);
		Node<T> prevNode = search(idx - 1);
		Node<T> nextNode = prevNode.next;

		prevNode.next = newNode;
		nextNode.prev = newNode;

		newNode.prev = prevNode;
		newNode.next = nextNode;

		size++;

	}

	void remove() {
		removeLast();
	}

	T removeFirst() {
		Node<T> headNode = head;

		if (headNode == null) { // size == 0
			throw new NoSuchElementException();
		}

		// 리턴할 data 임시저장
		T data = headNode.data;

		Node<T> headNext = headNode.next;

		// headNode 삭제, 다음 노드의 prev 삭제
		headNode.next = null;
		headNode.data = null;
		if (headNext != null) {
			headNext.prev = null;
		}

		// head는 headNext가 null일 수도 있음으로 if문으로 나눠서 생각할 필요 없음
		head = headNext;
		size--;

		// remove하여 size가 0이되면 마지막 노드도 null 지정
		if (size == 0) {
			tail = null;
		}

		return data;

	}

	T removeLast() {
		Node<T> tailNode = tail;

		if (size == 0) { // tail == null
			throw new NoSuchElementException();
		}

		T data = tailNode.data;

		Node<T> prevNode = tailNode.prev;

		tailNode.prev = null;
		tailNode.data = null;
		if (prevNode != null) {
			prevNode.next = null;
		}

		tail = prevNode;
		size--;

		if (size == 0) {
			head = null;
		}

		return data;
	}

	T remove(int idx) {

		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (idx == 0) {
			T data = head.data;
			removeFirst();
			return data;
		}

		if (idx == size) {
			T data = tail.data;
			removeLast();
			return data;
		}

		Node<T> removeNode = search(idx);
		Node<T> prevNode = removeNode.prev;
		Node<T> nextNode = removeNode.next;

		T data = removeNode.data;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;

		removeNode.prev = null;
		removeNode.data = null;
		removeNode.next = null;

		size--;

		// head, tail은 removeFirst, removeLast 경우에서 갱신함으로 갱신할 필요 없음

		return data;
	}

	boolean remove(T data) {
		Node<T> x = head;

		for (; x != null; x = x.next) {
			if (data.equals(x)) {
				break;
			}
		}

		if (x == null) {
			return false;
		}

		if (x.equals(head)) {
			remove();
			return true;
		}

		if (x.equals(tail)) {
			removeLast();
			return true;
		}

		Node<T> prevNode = x.prev;
		Node<T> nextNode = x.next;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;

		x.prev = null;
		x.data = null;
		x.next = null;

		size--;
		return true;
	}

}

class Node<T> {
	Node prev;
	Node next;
	T data;

	Node() {

	}

	Node(T data) {
		this.prev = null;
		this.next = null;
		this.data = data;
	}
}

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = sc.nextInt();
			MyDLinkedList<Integer> linkedList = new MyDLinkedList();
			for (int i = 0; i < N; i++) {
				linkedList.add(sc.nextInt());
			}

			int M = sc.nextInt();
			for (int i = 0; i < M; i++) {
				char c = sc.next().toCharArray()[0];
				int x = sc.nextInt();
				int y = sc.nextInt();
				for (int j = 0; j < y; j++) {
					linkedList.add(x+j, sc.nextInt());
				}
			}

			StringBuilder sb = new StringBuilder();
			Node n = linkedList.head;
			for (int i = 0; i < 10; i++) {
				sb.append(n.data + " ");
				n = n.next;
			}

			System.out.printf("#%d %s \n", test_case, sb);
		}
	}
}