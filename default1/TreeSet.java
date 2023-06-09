package default1;
//чтобы понять, что я сделал в этой программе, прочитай файл "read this"
public class TreeSet<v>{
	private class Node{
		private v value;
		//значение
		private Node left,right;
		//левое и правое дерево
		private Node(v val){
			this.value=val;
			left=null;
			right=null;
		}
		//конструктор
	}
	private Node root=null;
	//изначально корень дерева пустой
	private int size=0;
	//размер изначально тоже равен нулю
	private Node findNode(Object val) {
		//это приватный метод чтобы упростить роботу с другими методами
		Node thisNode=root;
		//начинаем искать с корня дерева
		Comparable<v> val1=(Comparable<v>) val;
		//Comparable нам нужен чтобы мы смогули использовать метод compareTo
		while(thisNode!=null) {
			int num=val1.compareTo(thisNode.value);
			//в зависимости от того, что он выдаст (отцательное, положительное и 0) будем сравнивать и в итоге вернем нужную Node
			if(num<0) {
				thisNode=thisNode.left;
			}
			else if(num>0) {
				thisNode=thisNode.right;
			}
			else if(num==0) {
				return thisNode;
			}
		}
		return null;
	}
	public boolean contains(v val) {
		// метод contains возвращает true и false, если возвращает true, то число есть в дереве, false, если его нет
		Node thisNode=root;
		Comparable<v> val1=(Comparable<v>) val;
		while(thisNode!=null) {
			int num=val1.compareTo(thisNode.value);
			if(num<0) {
				thisNode=thisNode.left;
			}
			else if(num>0) {
				thisNode=thisNode.right;
			}
			else if(num==0) {
				return true;
			}
		}
		return false;
	}
	public void add(v val) {
		if(root==null) {
			//если корень у нас пустой, то просто делаем его новой Node и передаем ему наше значение
			root=new Node(val);
			size++;
			//при добавлении размер мы увеличиваем, при удалении - уменьшаем
		}
		helpAdd(val,root);
		//если мы не попали в первую условную конструкцию с root - вызываем другой метод, который поможет нам с добвлением элемента в дерево
	}
	private void helpAdd(v val, Node node) {
		Comparable<v> val1=(Comparable<v>) val;
		int num=val1.compareTo(node.value);
		if(num<0) {
			if(node.left==null) {
				node.left=new Node(val);
				size++;
			}
			helpAdd(val,node.left);
		}
		else if(num>0) {
			if(node.right==null) {
				node.right=new Node(val);
				size++;
			}
			helpAdd(val,node.right);
		}
		else if(num==0) {
			node.value=val;
		}
	}
	public v remove(v val) {
		Node children=findNode(val);
		//children - текущая Node 
		if(children==null) {
			//удалять нечего, т.к. она пустая, значит мы возвращаем null
			return null;
		}
		if(size==1) {
			//первый элемент в дереве - всегда корень, а значит, что если у нас 1 элемент, то нам нужно удалить root
			root=null;
			size--;
			//при удалении мы всегда уменьшаем размер дерева
			return children.value;
			//возвращаем значение текущей Node
		}
		Node parent=findParent(val);
		//находим "родителя" текущей Node
		if(children.left!=null&&children.right!=null) {
			//если левое и правое дерево от текущей Node пустые - работаем с ними и возвращаем удаленный элемент
			Node find=find(children.right);
			Node helpFind=findParent(find.value);
			removeHelp(find,helpFind);
			v oldVal=children.value;
			children.value=find.value;
			return oldVal;
		}
		else {
			//во всех остальных случаях вызваем метод removeHelp
			return removeHelp(children,parent);
		}
	}
	private Node findParent(Object val) {
		//этот метод помогает нам найти родителя текущей Node (children)
		Comparable<v> val1=(Comparable<v>) val;
		Node children=root;
		Node parent=root;
		while(children!=null) {
			int num=val1.compareTo(children.value);
			if(num<0) {
				parent=children;
				children=children.left;
			}
			if(num>0) {
				parent=children;
				children=children.right;
			}
			if(num==0) {
				return parent;
			}
		}
		return null;
	}
	private v removeHelp(Node children,Node parent) {
		//этот метод помогает нам удалить элемент
		if(children.left==null&children.right==null) {
			if(parent.left==children) {
				parent.left=null;
			}
			if(parent.right==children) {
				parent.right=null;
			}
			size--;
			return children.value;
		}
		if(parent.left==null) {
			if(parent.left==children) {
				parent.left=children.right;
			}
			if(parent.right==children) {
				parent.right=children.right;
			}
			size--;
			return children.value;
		}
		if(children.right==null) {
			if(parent.left==children) {
				parent.left=children.left;
			}
			if(parent.right==children) {
				parent.right=children.left;
			}
			size--;
			return children.value;
		}
		return null;
	}
	private Node find(Node node) {
		while(node.left!=null) {
			node=node.left;
		}
		return node;
	}
}
