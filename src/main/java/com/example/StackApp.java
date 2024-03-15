// package che lo contiene
package com.example;

// classe App con entry-point, punto di ingresso e inizio esecuzione del programma
class StackApp { // contiene un main() entry-point
    public static void main(String[] args) { // entry-point, inizio da qui

        System.out.println("--- Tests -------------------");

        Stack<Integer> stack = testStackCreation(); // TEST: crea stack

        System.out.println("-----------------------------");

        testPushPop(stack); // TEST: prova inserimenti e rimozioni

        System.out.println("-----------------------------");

        stack.push(1);
        stack.push(2);
        testAverage(stack); // TEST: calcola una statistica di media dei contenuti
    }

    // prova inserimenti e rimozioni
    public static void testPushPop(Stack<Integer> stack) {
        try {
            // inserimenti in ordine
            stack.push(11);
            stack.push(22);
            stack.push(33);
            // rimozioni in ordine inverso (Last In , First Out)
            System.out.println("popped:" + stack.pop());
            System.out.println("popped:" + stack.pop());
            System.out.println("popped:" + stack.pop());
            System.out.println("popped:" + stack.pop());
        } catch (ArrayIndexOutOfBoundsException exception) {
            // stack-trace informativo dell'eccezione emesso in output standard
            exception.printStackTrace();
        }
    }

    // crea stack di Integer per prova
    public static Stack<Integer> testStackCreation() {
        Stack<Integer> stack = null; // riferimento inizialmente nullo
        try {
            Class<? extends Number> type = Integer.class; // tipo di dato contenuto
            // costruttore di tipo capienza massima e tipo contenuti
            stack = new Stack<Integer>(4, type);
        } catch (Exception exception) {
            // stack-trace informativo dell'eccezione emesso in output standard
            exception.printStackTrace();
        }
        return stack; // lo restituisce mediante uso di "return"
    }

    // in quanto static può fare calcoli anche su... chessò altri come Stack<Float>
    public static void testAverage(Stack<? extends Number> stack) {
        try {
            // media statistica
            System.out.println("getAverage() : " + Stack.getAverage(stack));
        } catch (Exception exception) {
            // stack-trace informativo dell'eccezione emesso in output standard
            exception.printStackTrace();
        }
    }
}

// classe Stack ovvero Pila
class Stack<N extends Number> { // impila numeri: N che estende da Number (che è classe astratta)
    private N[] array; // array a dimensione fissa: possibilità di over-flow
    private int count; // conta quanti ce ne sono, occupazione dello Stack

    // --------------------------------------
    // --- costruttori di oggetto di classe -

    // avendo array iniziale e conteggio iniziale
    @SuppressWarnings("unused")
    private Stack(N[] initialArray, int initialCount) throws Exception {
        // verifiche di validità del parametro initialCount
        if (initialCount < 0 || initialCount > initialArray.length)
            throw new Exception("initial count not valid!");

        // inizializza lo stato iniziale dell'oggetto "this"
        array = initialArray;
        count = initialCount;
    }

    // avendo array iniziale e conteggio iniziale a zero
    @SuppressWarnings("unused")
    private Stack(N[] initialEmptyArray) {
        // inizializza, stato iniziale
        array = initialEmptyArray;
        count = 0; // inizialmente vuoto, ce ne sono zero di elementi contenuti
    }

    @SuppressWarnings("unchecked")
    public Stack(int size, Class<? extends Number> componentType) {
        // caso di reflection: crea un array base avendo sia Tipo sia Lunghezza
        array = (N[]) java.lang.reflect.Array.newInstance(componentType, size);
        count = 0; // inizialmente vuoto, ce ne sono zero di elementi contenuti
    }

    // ----------------------------------------------
    // --- tipiche operazioni di coda: push & pop ---

    // operazione di inserimento di tipo "push"
    public void push(N item) {
        // gestione dello stack overflow
        if (count >= array.length)
            throw new ArrayIndexOutOfBoundsException("full stack, then 'push' op fails!");
        // inserimento. nella pila l'inserimento è all'ultimo.
        array[count++] = item; // nuovo ultimo elemento, poi incrementa il conteggio di occupazione
        // count++;
    }

    // operazione di rimozione di tipo "pop"
    public N pop() throws ArrayIndexOutOfBoundsException {
        if (count <= 0)
            throw new ArrayIndexOutOfBoundsException("empty stack, then 'pop' op fails!");
        // --count;
        return array[--count]; // rimuovi ultimo elemento, prima decrementa il conteggio di occupazione
    }

    // --------------------------------------
    // --- media statistica -----------------

    // uso statico data la parola chiave "static". pila di qualcosa di numerico, in
    // genere (uso di "?")
    public static double getAverage(Stack<? extends Number> stack) throws Exception {
        int count = stack.getCount();
        if (count <= 0) // non si pò dividere per zero
            throw new Exception("empty stack, so no average op possible!");
        // inizializza accumulatore (variabile accumulatore)
        double average = 0;
        // conta sommando nell'accumulatore
        for (int index = 0; index < count; index++) // uno per uno, itera, percorre
            average += stack.getByIndex(index).doubleValue(); // accumula con incremento
        return average / count; // totale somma diviso quanti ne ha sommati. vedasi anche: divisione per zero.
    }

    // --------------------------------------
    // --- getters --------------------------

    public int getCount() {
        return count; // occupazione, quanti ne contiene
    }

    // dato un numero intero usato come indice prendi quel che c'è
    public N getByIndex(int index) throws IndexOutOfBoundsException { // indice "fuori dai limiti"
        // indice integer, numero intero, senza decimali.
        return array[index]; // eccezioni possibili di indice "fuori dai limiti"
    }

}
