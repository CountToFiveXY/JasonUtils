package JavaUtils;

import java.util.*;

public class Collections {
    public static boolean isEqualCollection(Collection<?> a, Collection<?> b) {
        if (a.size() != b.size()) {
            return false;
        } else {
            CardinalityHelper<Object> helper = new CardinalityHelper(a, b);
            if (helper.cardinalityA.size() != helper.cardinalityB.size()) {
                return false;
            } else {
                Iterator i$ = helper.cardinalityA.keySet().iterator();

                Object obj;
                do {
                    if (!i$.hasNext()) {
                        return true;
                    }

                    obj = i$.next();
                } while (helper.freqA(obj) == helper.freqB(obj));

                return false;
            }
        }
    }

    public static <E> boolean isEqualCollection(Collection<? extends E> a, Collection<? extends E> b, final Equator<? super E> equator) {
        if (equator == null) {
            throw new NullPointerException("Equator must not be null.");
        } else if (a.size() != b.size()) {
            return false;
        } else {
            Transformer<E, ?> transformer = new Transformer() {
                public EquatorWrapper<?> transform(Object input) {
                    return new EquatorWrapper(equator, input);
                }
            };
            return isEqualCollection(collect((Iterable)a, transformer), collect((Iterable)b, transformer));
        }
    }

    private static <I, O> Collection<O> collect(Iterable<I> inputCollection, Transformer<? super I, ? extends O> transformer) {
        Collection<O> answer = inputCollection instanceof Collection ? new ArrayList(((Collection)inputCollection).size()) : new ArrayList();
        return collect((Iterable)inputCollection, transformer, answer);
    }

    private static <I, O, R extends Collection<? super O>> R collect(Iterable<? extends I> inputCollection, Transformer<? super I, ? extends O> transformer, R outputCollection) {
        return inputCollection != null ? collect(inputCollection.iterator(), transformer, outputCollection) : outputCollection;
    }

    private static <I, O, R extends Collection<? super O>> R collect(Iterator<? extends I> inputIterator, Transformer<? super I, ? extends O> transformer, R outputCollection) {
        if (inputIterator != null && transformer != null) {
            while(inputIterator.hasNext()) {
                I item = inputIterator.next();
                O value = transformer.transform(item);
                outputCollection.add(value);
            }
        }

        return outputCollection;
    }

    private static <O> Map<O, Integer> getCardinalityMap(Iterable<? extends O> coll) {
        Map<O, Integer> count = new HashMap();
        Iterator i$ = coll.iterator();

        while(i$.hasNext()) {
            O obj = (O)i$.next();
            Integer c = count.get(obj);
            if (c == null) {
                count.put(obj, 1);
            } else {
                count.put(obj, c + 1);
            }
        }

        return count;
    }

    public interface Transformer<I, O> {
        O transform(I var1);
    }

    private static class CardinalityHelper<O> {
        final Map<O, Integer> cardinalityA;
        final Map<O, Integer> cardinalityB;

        CardinalityHelper(Iterable<? extends O> a, Iterable<? extends O> b) {
            this.cardinalityA = getCardinalityMap(a);
            this.cardinalityB = getCardinalityMap(b);
        }

        public final int max(Object obj) {
            return Math.max(this.freqA(obj), this.freqB(obj));
        }

        public final int min(Object obj) {
            return Math.min(this.freqA(obj), this.freqB(obj));
        }

        public int freqA(Object obj) {
            return this.getFreq(obj, this.cardinalityA);
        }

        public int freqB(Object obj) {
            return this.getFreq(obj, this.cardinalityB);
        }

        private final int getFreq(Object obj, Map<?, Integer> freqMap) {
            Integer count = freqMap.get(obj);
            return count != null ? count : 0;
        }
    }

    private static class EquatorWrapper<O> {
        private final Equator<? super O> equator;
        private final O object;

        public EquatorWrapper(Equator<? super O> equator, O object) {
            this.equator = equator;
            this.object = object;
        }

        public O getObject() {
            return this.object;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EquatorWrapper)) {
                return false;
            } else {
                EquatorWrapper<O> otherObj = (EquatorWrapper)obj;
                return this.equator.equate(this.object, otherObj.getObject());
            }
        }

        public int hashCode() {
            return this.equator.hash(this.object);
        }
    }
}
