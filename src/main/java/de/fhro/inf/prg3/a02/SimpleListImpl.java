package de.fhro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    private Element head;
    private int size = 0;

    public SimpleListImpl(){
        head = null;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }

    private class SimpleIteratorImpl implements Iterator {
        private Element current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }


    private static class Element {
        private Object item;
        private Element next;

        Element(Object o) {
            this.item = o;
            this.next = null;
        }

        public Object getItem() {
            return item;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    @Override
    public void add(Object o) {
        if(head == null)
            head = new Element(o);
        else {
            Element current = head;
            while(current.getNext() != null)
                current = current.getNext();

            current.setNext(new Element(o));
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl result = new SimpleListImpl();
        for(Object o: this) {
            if(filter.include(o))
                result.add(o);
        }

        return result;
    }
}
