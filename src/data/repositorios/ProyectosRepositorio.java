/*
 * Copyright (c) 2022 Erick Castrillo Arroyo <ecastrillo@edu.upolitecnica.cr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package data.repositorios;

import data.modelos.Estado;
import data.modelos.Proyecto;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ProyectosRepositorio {
    // Array de proyectos
    private ArrayList<Proyecto> proyectos;

    // Devolver todos los proyectos
    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    // Devolver un proyecto por su ID
    public Proyecto getProyecto(String id) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(id)) {
                return proyecto;
            }
        }
        return null;
    }

    // Agregar un proyecto
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    // Eliminar un proyecto
    public void eliminarProyecto(String id) {
        proyectos.remove(getProyecto(id));
    }

    // Actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto) {
        Proyecto proyectoActual = getProyecto(proyecto.getId());
        proyectoActual.setNombre(proyecto.getNombre());
        proyectoActual.setDescripción(proyecto.getDescripción());
        proyectoActual.setResponsableId(proyecto.getResponsableId());
        proyectoActual.setEstado(proyecto.getEstado());
    }

    // Devolver los proyectos de un usuario
    public ArrayList<Proyecto> getProyectosDeUsuario(String usuarioId) {
        ArrayList<Proyecto> proyectosDeUsuario = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getResponsableId().equals(usuarioId)) {
                proyectosDeUsuario.add(proyecto);
            }
        }
        return proyectosDeUsuario;
    }

    // Devolver los proyectos de un usuario en un estado
    public ArrayList<Proyecto> getProyectosDeUsuarioEnEstado(String usuarioId, Estado estado) {
        ArrayList<Proyecto> proyectosDeUsuarioEnEstado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getResponsableId().equals(usuarioId) && proyecto.getEstado().equals(estado)) {
                proyectosDeUsuarioEnEstado.add(proyecto);
            }
        }
        return proyectosDeUsuarioEnEstado;
    }

    // Devolver los proyectos en un estado
    public ArrayList<Proyecto> getProyectosPorEstado(Estado estado) {
        ArrayList<Proyecto> proyectosEnEstado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEstado().equals(estado)) {
                proyectosEnEstado.add(proyecto);
            }
        }
        return proyectosEnEstado;
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(proyectos, "proyectos.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    @SuppressWarnings("unchecked")
    public void cargarCambios() {
        Object datos = Serializador.deserializar("proyectos.dat");
        if(datos != null){
            this.proyectos = (ArrayList<Proyecto>) datos;
        } else {
            this.proyectos = new ArrayList<>();
        }
    }

    // Constructor
    public ProyectosRepositorio() {
        cargarCambios();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                guardarCambios();
                System.out.println("Guardando cambios en los proyectos...");
            }
        }, "Shutdown-thread"));
    }

    /**
     * Trims the capacity of this {@code ArrayList} instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an {@code ArrayList} instance.
     */
    public void trimToSize() {
        getProyectos().trimToSize();
    }

    /**
     * Increases the capacity of this {@code ArrayList} instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        getProyectos().ensureCapacity(minCapacity);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return getProyectos().size();
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return getProyectos().isEmpty();
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o) {
        return getProyectos().contains(o);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o
     */
    public int indexOf(Object o) {
        return getProyectos().indexOf(o);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o
     */
    public int lastIndexOf(Object o) {
        return getProyectos().lastIndexOf(o);
    }

    /**
     * Returns a shallow copy of this {@code ArrayList} instance.  (The
     * elements themselves are not copied.)
     *
     * @return a clone of this {@code ArrayList} instance
     */
    @Override
    public Object clone() {
        return getProyectos().clone();
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    public Object[] toArray() {
        return getProyectos().toArray();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     *
     * <p>If the list fits in the specified array with room to spare
     * (i.e., the array has more elements than the list), the element in
     * the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of the
     * list <i>only</i> if the caller knows that the list does not contain
     * any null elements.)
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    public <T> T[] toArray(T[] a) {
        return getProyectos().toArray(a);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Proyecto get(int index) {
        return getProyectos().get(index);
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Proyecto set(int index, Proyecto element) {
        return getProyectos().set(index, element);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param proyecto element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(Proyecto proyecto) {
        return getProyectos().add(proyecto);
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, Proyecto element) {
        getProyectos().add(index, element);
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Proyecto remove(int index) {
        return getProyectos().remove(index);
    }

    /**
     * {@inheritDoc}
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        return getProyectos().equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getProyectos().hashCode();
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(Object o) {
        return getProyectos().remove(o);
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
        getProyectos().clear();
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified collection is this list, and this
     * list is nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(Collection<? extends Proyecto> c) {
        return getProyectos().addAll(c);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends Proyecto> c) {
        return getProyectos().addAll(index, c);
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException   if the class of an element of this list
     *                              is incompatible with the specified collection
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *                              specified collection does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see Collection#contains(Object)
     */
    public boolean removeAll(Collection<?> c) {
        return getProyectos().removeAll(c);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes from this list all
     * of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws ClassCastException   if the class of an element of this list
     *                              is incompatible with the specified collection
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *                              specified collection does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see Collection#contains(Object)
     */
    public boolean retainAll(Collection<?> c) {
        return getProyectos().retainAll(c);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @param index
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterator<Proyecto> listIterator(int index) {
        return getProyectos().listIterator(index);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @see #listIterator(int)
     */
    public ListIterator<Proyecto> listIterator() {
        return getProyectos().listIterator();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Proyecto> iterator() {
        return getProyectos().iterator();
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations.
     *
     * <p>This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for {@link #indexOf(Object)} and
     * {@link #lastIndexOf(Object)}, and all of the algorithms in the
     * {@link Collections} class can be applied to a subList.
     *
     * <p>The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex
     * @param toIndex
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException  {@inheritDoc}
     */
    public List<Proyecto> subList(int fromIndex, int toIndex) {
        return getProyectos().subList(fromIndex, toIndex);
    }

    /**
     * @param action
     * @throws NullPointerException {@inheritDoc}
     */
    public void forEach(Consumer<? super Proyecto> action) {
        getProyectos().forEach(action);
    }

    /**
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED},
     * {@link Spliterator#SUBSIZED}, and {@link Spliterator#ORDERED}.
     * Overriding implementations should document the reporting of additional
     * characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @since 1.8
     */
    public Spliterator<Proyecto> spliterator() {
        return getProyectos().spliterator();
    }

    /**
     * @param filter
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean removeIf(Predicate<? super Proyecto> filter) {
        return getProyectos().removeIf(filter);
    }

    public void replaceAll(UnaryOperator<Proyecto> operator) {
        getProyectos().replaceAll(operator);
    }

    public void sort(Comparator<? super Proyecto> c) {
        getProyectos().sort(c);
    }

    /**
     * {@inheritDoc}
     *
     * @param c
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @implSpec This implementation iterates over the specified collection,
     * checking each element returned by the iterator in turn to see
     * if it's contained in this collection.  If all elements are so
     * contained {@code true} is returned, otherwise {@code false}.
     * @see #contains(Object)
     */
    public boolean containsAll(Collection<?> c) {
        return getProyectos().containsAll(c);
    }

    /**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * ({@code "[]"}).  Adjacent elements are separated by the characters
     * {@code ", "} (comma and space).  Elements are converted to strings as
     * by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection
     */
    @Override
    public String toString() {
        return getProyectos().toString();
    }

    /**
     * Returns an array containing all of the elements in this collection,
     * using the provided {@code generator} function to allocate the returned array.
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @param generator a function which produces a new array of the desired
     *                  type and the provided length
     * @return an array containing all of the elements in this collection
     * @throws ArrayStoreException  if the runtime type of any element in this
     *                              collection is not assignable to the {@linkplain Class#getComponentType
     *                              runtime component type} of the generated array
     * @throws NullPointerException if the generator function is null
     * @apiNote This method acts as a bridge between array-based and collection-based APIs.
     * It allows creation of an array of a particular runtime type. Use
     * {@link #toArray()} to create an array whose runtime type is {@code Object[]},
     * or use {@link #toArray(Object[]) toArray(T[])} to reuse an existing array.
     *
     * <p>Suppose {@code x} is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(String[]::new);</pre>
     * @implSpec The default implementation calls the generator function with zero
     * and then passes the resulting array to {@link #toArray(Object[]) toArray(T[])}.
     * @since 11
     */
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return getProyectos().toArray(generator);
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a sequential {@code Stream} over the elements in this collection
     * @implSpec The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    public Stream<Proyecto> stream() {
        return getProyectos().stream();
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a possibly parallel {@code Stream} over the elements in this
     * collection
     * @implSpec The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    public Stream<Proyecto> parallelStream() {
        return getProyectos().parallelStream();
    }

    /**
     * Returns an unmodifiable list containing zero elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @return an empty {@code List}
     * @since 9
     */
    public static <E> List<E> of() {
        return List.of();
    }

    /**
     * Returns an unmodifiable list containing one element.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the single element
     * @return a {@code List} containing the specified element
     * @throws NullPointerException if the element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1) {
        return List.of(e1);
    }

    /**
     * Returns an unmodifiable list containing two elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2) {
        return List.of(e1, e2);
    }

    /**
     * Returns an unmodifiable list containing three elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3) {
        return List.of(e1, e2, e3);
    }

    /**
     * Returns an unmodifiable list containing four elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4) {
        return List.of(e1, e2, e3, e4);
    }

    /**
     * Returns an unmodifiable list containing five elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
        return List.of(e1, e2, e3, e4, e5);
    }

    /**
     * Returns an unmodifiable list containing six elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        return List.of(e1, e2, e3, e4, e5, e6);
    }

    /**
     * Returns an unmodifiable list containing seven elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        return List.of(e1, e2, e3, e4, e5, e6, e7);
    }

    /**
     * Returns an unmodifiable list containing eight elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @param e8 the eighth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    /**
     * Returns an unmodifiable list containing nine elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1 the first element
     * @param e2 the second element
     * @param e3 the third element
     * @param e4 the fourth element
     * @param e5 the fifth element
     * @param e6 the sixth element
     * @param e7 the seventh element
     * @param e8 the eighth element
     * @param e9 the ninth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    /**
     * Returns an unmodifiable list containing ten elements.
     * <p>
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param e1  the first element
     * @param e2  the second element
     * @param e3  the third element
     * @param e4  the fourth element
     * @param e5  the fifth element
     * @param e6  the sixth element
     * @param e7  the seventh element
     * @param e8  the eighth element
     * @param e9  the ninth element
     * @param e10 the tenth element
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null}
     * @since 9
     */
    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    /**
     * Returns an unmodifiable list containing an arbitrary number of elements.
     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
     *
     * @param elements the elements to be contained in the list
     * @return a {@code List} containing the specified elements
     * @throws NullPointerException if an element is {@code null} or if the array is {@code null}
     * @apiNote This method also accepts a single array as an argument. The element type of
     * the resulting list will be the component type of the array, and the size of
     * the list will be equal to the length of the array. To create a list with
     * a single element that is an array, do the following:
     *
     * <pre>{@code
     *     String[] array = ... ;
     *     List<String[]> list = List.<String[]>of(array);
     * }</pre>
     * <p>
     * This will cause the {@link List#of(Object) List.of(E)} method
     * to be invoked instead.
     * @since 9
     */
    @SafeVarargs
    public static <E> List<E> of(E... elements) {
        return List.of(elements);
    }

    /**
     * Returns an <a href="#unmodifiable">unmodifiable List</a> containing the elements of
     * the given Collection, in its iteration order. The given Collection must not be null,
     * and it must not contain any null elements. If the given Collection is subsequently
     * modified, the returned List will not reflect such modifications.
     *
     * @param coll a {@code Collection} from which elements are drawn, must be non-null
     * @return a {@code List} containing the elements of the given {@code Collection}
     * @throws NullPointerException if coll is null, or if it contains any nulls
     * @implNote If the given Collection is an <a href="#unmodifiable">unmodifiable List</a>,
     * calling copyOf will generally not create a copy.
     * @since 10
     */
    public static <E> List<E> copyOf(Collection<? extends E> coll) {
        return List.copyOf(coll);
    }
}
