# SCROLL HORIZONTAL #
### HorizontalScrollView ###
Este elemento sirve para realizar un desplazamiento de otros elementros que colocamos en su interior y realizar desplazamientos de forma horizontal. Tendremos que colocar su ancho, su alto y tenemos la opción de colocar una _scrollbar_ que nos sirva como referencia, en este ejemplo colocamos el valor como _none_ porque no queremos ver la barra de desplazamiento.
Dentro del _HorizontalScrollView_ colocamos un _LinearLayout_ para colocar los elementos de forma horizontal.

```xml 
      <HorizontalScrollView
	    android:layout_width="250dp"
	    android:layout_height="wrap_content"
	    android:layout_gravity="center_horizontal"
	    android:scrollbars="none">
	    
	    <LinearLayout
	        android:layout_width="fill_parent"
	        android:layout_height="wrap_content"
	        android:orientation="horizontal">
```
 
