/* 
given a tree as a parameter, will return an  inorder traversal of that tree.Your implementation should throw an 
IllegalArgumentException if the tree is null.Your implementation must implement the FlattenTree interface For example a tree like:

  /|\
 1 | 6
  /|\
 5 4 9
would result in the list [1,5,4,9,6].

*/

import java.util.*;
import java.lang.*;
import java.util.List;

/**
 * A type that stores three values of the same type.
 */
 class Triple<V> {
	
	private final V l, m, r;

	public Triple(V l, V m, V r) {
		this.l = l;
        this.m = m;
		this.r = r;
	}

	public V left() {
		return l;
	}

    public V middle() {
        return m;
    }

	public V right() {
		return r;
	}
	
}


/**
 * A type which stores one of either of two types of value, but not both.
 *
 */
 class Either<A,B> {
	
	/**
	 * Constructs a left-type Either
	 */
	public static <A> Either left(A a) {
		if (a == null) throw new IllegalArgumentException();
		return new Either(a, null);
	}
	
	/**
	 * Constructs a right-type Either
	 */
	public static <B> Either right(B b) {
		if (b == null) throw new IllegalArgumentException();
		return new Either(null, b);
	}
	
	
	private final A a;
	private final B b;
	
	private Either(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Applies function f to the contained value if it is a left-type and returns the result. Throws an IllegalStateException if this is a right-type Either.
	 */
	public<T> T ifLeft(Function<A,T> f) {
		if (!this.isLeft()) {
			throw new IllegalStateException();
		}
		return f.apply(a);
		
	}

	/**
	 * Applies function f to the contained value if it is a right-type and returns the result. Throws an IllegalStateException if this is a left-type Either.
	 */
	public<T> T ifRight(Function<B,T> f) {
		if (this.isLeft()) {
			throw new IllegalStateException();
		}
		return f.apply(b);
		
	}
	
	/**
	 * @return true if this is a left, false if it is a right
	 */
	public boolean isLeft() {
		return b == null;
	}

}


interface FlattenTree<T> {

	/**
	 * 
	 * @param tree the Tree to flatten
	 * @return a list containing all the leaf values in t, in left-to-right order
	 * @throws IllegalArgumentException if t is null
	 */
	public List<T> flattenInOrder(Tree<T> tree);
	
}

interface Function<P, R> {

        public R apply(P p);
}

class GetFunction<P, R> implements Function<P, R> {
	public R apply(P p) {
		return (R) p;
	}
}

 interface Tree<T> {
	
	Either<T, Triple<Tree<T>>> get();


	static final class Leaf<T> implements Tree<T> {
	 
		public static <T> Leaf<T> leaf (T value) {
			return new Leaf<T>(value);
		}
		
		private final T t;
		
		public Leaf(T t) {
			this.t = t;
		}
		
		@Override
		public Either<T, Triple<Tree<T>>> get() {
			return Either.left(t);
		}
	}
		
	static final class Node<T> implements Tree<T> {
		public static <T> Tree<T> tree (T left, T middle, T right) {
			return new Node<T>(Leaf.leaf(left), Leaf.leaf(middle), Leaf.leaf(right));
		}

		private final Triple<Tree<T>> branches;

		public Node(Tree<T> left, Tree<T> middle, Tree<T> right) {
			this.branches = new Triple<Tree<T>>(left, middle, right);
		}
	
		@Override
		public Either<T, Triple<Tree<T>>> get() {
			return Either.right(branches);
		}
	}
}


class MyFlattenTree<T> implements FlattenTree<T> {

	public List<T> flattenInOrder(Tree<T> tree) {
		if (tree == null) {
			throw new IllegalArgumentException();
		}
		LinkedList<T> result = new LinkedList<T>();
		inorder(tree, result);
		
		return result;
	}
	
	private void inorder(Tree<T> current, LinkedList<T> result) {
		GetFunction<T,T> leaf_get_function = new GetFunction<T,T>();
		GetFunction<Triple<Tree<T>>,Triple<Tree<T>>> triple_get_function = new GetFunction<Triple<Tree<T>>,Triple<Tree<T>>>();
		if (current.get().isLeft()) {
			/* A leaf */
			result.add(current.get().ifLeft(leaf_get_function));
		} else {
			/* traverse left */
			inorder(current.get().ifRight(triple_get_function).left(), result);
			/* traverse middle */
			inorder(current.get().ifRight(triple_get_function).middle(), result);
			/* traverse right */
			inorder(current.get().ifRight(triple_get_function).right(), result);
		}
	}
}

class FlattenTernaryTree
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Tree<Integer> node_one = new Tree.Leaf<Integer>(1);
		Tree<Integer> node_five = new Tree.Leaf<Integer>(5);
		Tree<Integer> node_four = new Tree.Leaf<Integer>(4);
		Tree<Integer> node_nine = new Tree.Leaf<Integer>(9);
		Tree<Integer> node_six = new Tree.Leaf<Integer>(6);
		Tree<Integer> node_five_four_nine = new Tree.Node<Integer>(node_five,node_four,node_nine);
		Tree<Integer> root = new Tree.Node<Integer>(node_one,node_five_four_nine,node_six);
		MyFlattenTree<Integer> flatten = new MyFlattenTree<Integer>();
		System.out.println(flatten.flattenInOrder(root));
	}
}
/*********************************/
Time Complexity O(3^n)
Space Complexity O(n) , where n is number of nodes in tree.
Run Here http://ideone.com/trzycV
/*********************************/
