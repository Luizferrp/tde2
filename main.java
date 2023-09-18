import java.util.Random;
import java.util.Scanner;
public class main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("bem vindo ao Hanoi");
    System.out.print("qual tamanho da pilha voce gostaria de tentar?:");
    int escolha = scanner.nextInt();
    System.out.print("quantas torres vc gostaria de ter?: ");
    int escolha2 = scanner.nextInt();
    Hanoi jogo = new Hanoi(escolha2, escolha);
    jogo.imprime();
    System.out.println("deseja que o algoritimo resolva? [(1)sim/(any)não]");
    int escolha6 = scanner.nextInt();
    if (escolha6 == 1) {
      jogo.resolverTorreHanoi();
      jogo.imprime();
      return;
    } 
    while (true){
      jogo.imprime(); 
      System.out.println("Prefere: [(1)sair/(any)mover]\n");
      int escolha3 = scanner.nextInt();
      if (escolha3 == 1){
        return;
      }
      System.out.print("torre de origem:");
      int escolha4 = scanner.nextInt();
      System.out.print("torre de destino:");
      int escolha5 = scanner.nextInt();
      jogo.move(escolha4, escolha5);
    }
    
  }
  public static void main2(String[] args){
  //teste Node
  Node<Integer> tnode = new Node<Integer>(9);
  System.out.println(tnode.getValue());
  //teste Pilha
  Pilha<Integer> tpilha = new Pilha<Integer>();
  tpilha.add(9);
  tpilha.imprime();
  tpilha.add(12);
  tpilha.add(2);
  tpilha.imprime();
  tpilha.remove();
  tpilha.imprime();
  tpilha.remove();
  tpilha.imprime();
  //teste TorreDeHanoi
  TorreDeHanoi tTorreDeHanoi = new TorreDeHanoi();
  tTorreDeHanoi.add(10);
  tTorreDeHanoi.add(3);
  System.out.println("tentando add um numero maior");
  tTorreDeHanoi.add(12);
  tTorreDeHanoi.imprime();
  System.out.println("add numero maior via addOutRules");
  tTorreDeHanoi.addOutRules(12);
  tTorreDeHanoi.imprime();
  //teste Hanoi
  Hanoi jogo = new Hanoi(4, 10);
  jogo.imprime();
  //jogo.solve();
  //jogo.imprime();
  jogo.resolverTorreHanoi();
  jogo.imprime();
    return;
 // jogo.move(0,1);
  //jogo.imprime();
  //jogo.move(0,1);
  //jogo.imprime();
  }
}

class Hanoi{
  private TorreDeHanoi[] torres; 
  private int size;
  private int t;
  private Random n;

  public Hanoi(int t, int disks){
    this.n = new Random();
    this.size = disks;
    this.t = t;
    this.torres = new TorreDeHanoi[t];
    for (int i = 0; i < t; ++i){
      this.torres[i] = new TorreDeHanoi();
    }
    for (int i = 0; i < disks; i++){
     // ((TorreDeHanoi)this.torres[0]).addOutRules(n.nextInt(disks));
     ((TorreDeHanoi)this.torres[0]).addOutRules(disks-i);

    }
  }
  public void moverTorre(int altura, int origem, int destino, int auxiliar) {    
    if (altura == 1) {
      move(origem, destino);
    } else {
      moverTorre(altura - 1, origem, auxiliar, destino);
      move(origem, destino);
      moverTorre(altura - 1, auxiliar, destino, origem);
    }
    }

  public void resolverTorreHanoi() {
    int numDiscos = this.size;
      moverTorre(numDiscos, 0, 2, 1);
  }
  public void move(int a, int b){
    this.torres[b].add(this.torres[a].getfirstInValue());
    System.out.println("movendo: " + this.torres[a].getfirstInValue() + ", de tore:" + a + ", para torre:"+b);
    this.torres[a].remove();
  }
  public void imprime(){
    for (int i = 0; i != this.t; i++){
        this.torres[i].imprime(); 
    }
  }
  public int genRand(int supLim){
    long seed = System.currentTimeMillis();
    seed = (seed * 1103515245 + 12345) % (1L << 31);
    return (int) (seed % supLim);
  }
  /*public void solve() {
    TorreDeHanoi from = this.torres[0];
    TorreDeHanoi to = this.torres[1];
    TorreDeHanoi aux = this.torres[2];

    if (from.getSize() == 0) {
      return;
    }
    solveRec(from, to, aux, from.getSize());
  }

  private void solveRec(TorreDeHanoi from, TorreDeHanoi to, TorreDeHanoi aux, int n) {
    if (n == 1) {
      Integer disk = from.getfirstOutValue();
      from.remove();
      to.add(disk);
      return;
    }

    solveRec(from, aux, to, n - 1);

    Integer disk = from.getfirstOutValue();
    from.remove();
    to.add(disk);

    solveRec(aux, to, from, n - 1);
  }*/ 
  public void solve() {
    System.out.println("bb");
    TorreDeHanoi toSolve = this.torres[0];
		TorreDeHanoi torre = new TorreDeHanoi();
		Node<?> node;
		while ((node = torre.getfirstInNode()) != null) {
			Integer value = (Integer) node.getValue();
			Integer[] values = new Integer[value];
			Node<?> next;
			while ((next = node.getNext()) != null) {
				Integer nextValue = (Integer) next.getValue();
				this.addValue(values, nextValue);
			}
		}
		torre.imprime();
	}

	public Integer[] addValue(Integer[] last, int newValue) {
		Integer[] newValues = new Integer[last.length + 1];
		newValues[last.length] = newValue;
		int idx = 0;
		while (idx < last.length) {
			newValues[idx] = last[idx];
			System.out.println("newValues " + newValues[idx]);
			idx++;
		}
		
		int temp = 0;
		for (int i = 0; i < newValues.length; i++) {
			for (int j = i + 1; j < newValues.length; j++) {
				if (newValues[i] > newValues[j]) {
					temp = newValues[i];
					newValues[i] = newValues[j];
					newValues[j] = temp;
				}
			}
		}

		return newValues;
	}
 /* public void solve(int t1, int t2){
    TorreDeHanoi toSolve = this.torres[t1];
    toSolve.imprime();
    TorreDeHanoi auxiliar = this.torres[t2];
    if (toSolve.getSize() == 0){
      return;
    }
    Integer temp = toSolve.getfirstOutValue();
    while(toSolve.getSize() != 0){
      temp = toSolve.getfirstOutValue();
      toSolve.remove();
      if (auxiliar.getSize() > 0){
        if (auxiliar.getfirstOutValue() > temp ){
          toSolve.add(auxiliar.getfirstOutValue());
          auxiliar.remove();
        }
      }
    }
    auxiliar.add((int)temp);
    while (auxiliar.getSize()!=0) {
      toSolve.add(auxiliar.getfirstOutValue());
      toSolve.remove();
    }
  }*/
}

class TorreDeHanoi extends Pilha<Integer> {
  @Override
  public Integer getfirstOutValue(){
    return (Integer) super.getfirstOutValue();
  }
  @Override
  public void add(Integer arg){
    if (this.getfirstInNode() != null) {
      if (arg < (Integer) this.getfirstInValue()){
        super.add(arg);
      } else {
        System.out.println("Erro: Não é permitido adicionar um número maior após um número menor.");
      }
    } else {
    super.add(arg);
    }
  }
  public void addOutRules(Integer arg){
    super.add(arg);
  }
}

class Pilha<T>{
  private Node<T> firstIn;
  private Node<T> firstOut;
  private int size;

  public void add(T arg){
    if (this.firstIn != null) {
      Node<T> cache = new Node<T>(arg);
      this.firstIn.setPrevNode(cache);
      cache.setNextNode(this.firstIn);
      this.firstIn = cache;
    } else {
      this.firstIn = new Node<T>(arg);
      this.firstOut = this.firstIn;
      size = 0;
    }
    size++;
  }
  public void remove(){
    Node<T> cache = this.firstIn;
    this.firstIn = (Node<T>) this.firstIn.getNext();
    cache.setValeu(null);
    cache.setPrevNode(null);
    cache.setNextNode(null);
    //this.firstIn.setPrevNode(null);
    size--;
  }
  public void imprime(){
    Node<T> atual = this.firstOut;
    System.out.print("[ ");
    for(int i = 0; i < this.size; i++){
      System.out.print(atual.getValue() + " ");
      atual = (Node<T>) atual.getPrev();
    }
    System.out.print("]\n");
  }
  public Object take(int n){
    Node<T> atual = this.firstOut;
    for(int i = 0; i > n; i++){
      atual = atual.getPrev();
    }
    return atual.getValue(); 
  }
  public Node<T> getfirstOutNode() {
		return this.firstOut;
	}

	public Node<T> getfirstInNode() {
		return this.firstIn;
	}

	public T getfirstOutValue() {
		return (T) this.firstOut.getValue();
	}

	public T getfirstInValue() {
      return (T) this.firstIn.getValue();
	}

	public int getSize() {
		return this.size;
	}
}

class Node<T>{
  private Node<T> prev;
  private T value;
  private Node<T> next;

  public Node(T value){
    this.value = value;
    this.prev = null;
    this.next = null;
  }
  public void setPrevNode(Node<T> node) {
    this.prev = (Node<T>) node;
  }
  public void setNextNode(Node<T> node){
    this.next = (Node<T>) node;
  }
  public void setValeu(T node){
    this.value = (T) node;
  }
  public Object getValue(){
    return this.value;
  }
  public Node<T> getPrev(){
    return this.prev;
  }
  public Node<T> getNext(){
    return this.next;
  }
}
