package co.edu.uniquindio.tienda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;



public class Tienda implements Serializable{
	
	public static Tienda tien = null;
	
	private String nombre;
    private String direccion;
    private String nit;
    private HashMap<String, Producto> productos;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, Administrador> administradores;
    private HashSet<CarritoCompras> carritoCompras;
    private LinkedList<Venta> historicoVentas;
    private TreeSet<Producto> inventarioProductos;
    
    public Tienda() {
        
    }

    public Tienda(String nombre, String direccion, String nit) {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        productos = new HashMap<>();
        clientes = new HashMap<>();
        administradores = new HashMap<>();
        carritoCompras = new HashSet<>();
        historicoVentas = new LinkedList<>();
        //inventarioProductos = new TreeSet<>((p1, p2) -> p1.getCantidad() - p2.getCantidad());
        inventarioProductos = new TreeSet<>();
    }
    
    //getters and setters

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the productos
	 */
	public HashMap<String, Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(HashMap<String, Producto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the clientes
	 */
	public HashMap<String, Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(HashMap<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the carritoCompras
	 */
	public HashSet<CarritoCompras> getCarritoCompras() {
		return carritoCompras;
	}

	/**
	 * @param carritoCompras the carritoCompras to set
	 */
	public void setCarritoCompras(HashSet<CarritoCompras> carritoCompras) {
		this.carritoCompras = carritoCompras;
	}

	/**
	 * @return the historicoVentas
	 */
	public LinkedList<Venta> getHistoricoVentas() {
		return historicoVentas;
	}

	/**
	 * @param historicoVentas the historicoVentas to set
	 */
	public void setHistoricoVentas(LinkedList<Venta> historicoVentas) {
		this.historicoVentas = historicoVentas;
	}

	/**
	 * @return the inventarioProductos
	 */
	public TreeSet<Producto> getInventarioProductos() {
		return inventarioProductos;
	}

	/**
	 * @param inventarioProductos the inventarioProductos to set
	 */
	public void setInventarioProductos(TreeSet<Producto> inventarioProductos) {
		this.inventarioProductos = inventarioProductos;
	}
	
	public Cliente buscarClientePorDocumento(String documento) {
		if (clientes.containsKey(documento)) {
	        // Devolver el cliente si se encuentra
	        return clientes.get(documento);
	    }
	    // Devolver null si no se encuentra ningún cliente con el documento proporcionado
	    return null;
	}
	
	public Administrador buscarAdminPorDocumento(String documento) {
		if (administradores.containsKey(documento)) {
	        // Devolver el administrador si se encuentra
	        return administradores.get(documento);
	    }
	    // Devolver null si no se encuentra ningún administrador con el documento proporcionado
	    return null;
	}
	
	public void restarCantidadProducto(Producto producto) {
	    Producto productoEnTienda = productos.get(producto.getCodigo());
	    productoEnTienda.setCantidad(productoEnTienda.getCantidad() - 1);
	    
	}
	
	public void agregarCantidadProducto(Producto producto) {
	    Producto productoEnTienda = productos.get(producto.getCodigo());
	    productoEnTienda.setCantidad(productoEnTienda.getCantidad() + 1);
	}
	
	/**
     * Método para crear una nueva venta y añadirla al historial de ventas.
     * @param cliente Cliente que realiza la compra.
     * @param detalleVenta Detalle de la venta.
     */
    public void agregarVentaNueva(String documento, Double total, ArrayList<DetalleVenta> detalleVenta) {
        String codigo = generarCodigo();
        // Obtener la fecha de hoy
        LocalDate fecha = LocalDate.now();
        Cliente clienteElegido=buscarClientePorDocumento(documento);
        Venta venta = new Venta(codigo, fecha, total, clienteElegido, detalleVenta);
        // Añadir la venta al historial de ventas
        historicoVentas.add(venta);
    }

	
	public void agregarProductoACarrito(Producto producto, String documento) {
		// Verificar si ya hay un carrito asociado a ese cliente con dicho documento
        CarritoCompras carritoExistente = null;
        for (CarritoCompras carrito : carritoCompras) {
            if (carrito.getCliente().getIdentificacion().equals(documento)) {
                carritoExistente = carrito;
                break;
            }
        }

        if (carritoExistente != null) {
            // Si ya existe un carrito para este cliente, añadir el producto al carrito existente
            carritoExistente.getListaProductos().add(producto);
        } else {
            // Si no existe un carrito para este cliente, crear un nuevo carrito
            Cliente cliente = clientes.get(documento);
            carritoExistente = new CarritoCompras(generarCodigo(), cliente, new ArrayList<>());
            carritoExistente.getListaProductos().add(producto);
            carritoCompras.add(carritoExistente);
        }
	}
	
	public void eliminarProductoDeCarrito(Producto producto, String documento) {
	    // Buscar el carrito de compras del cliente
	    CarritoCompras carritoCliente = null;
	    for (CarritoCompras carrito : carritoCompras) {
	        if (carrito.getCliente().getIdentificacion().equals(documento)) {
	            carritoCliente = carrito;
	            break;
	        }
	    }
	    
	    // Si se encontró el carrito del cliente
	    if (carritoCliente != null) {
	        // Buscar el producto en el carrito del cliente
	        ArrayList<Producto> listaProductos = carritoCliente.getListaProductos();
	        for (Producto productoCarrito : listaProductos) {
	            // Si se encuentra el producto en el carrito, eliminarlo y salir del bucle
	            if (productoCarrito.getCodigo().equals(producto.getCodigo())) {
	                listaProductos.remove(productoCarrito);
	                break;
	            }
	        }
	    }
	}

	
	public void registrarCliente(String documento, String nombre, String direccion) {
		if (!clientes.containsKey(documento)) {
	        Cliente clienteNuevo = new Cliente(nombre, documento, direccion);
	        clientes.put(documento, clienteNuevo);
	        System.out.println("Cliente registrado con éxito.");
	    } else {
	        System.out.println("El cliente con documento " + documento + " ya está registrado.");
	    }
	}
	
	public void registrarAdmin(String documento, String nombre, String direccion) {
		if (!administradores.containsKey(documento)) {
	        Administrador adminNuevo = new Administrador(nombre, documento, direccion);
	        administradores.put(documento, adminNuevo);
	        System.out.println("Admin registrado con éxito.");
	    } else {
	        System.out.println("El Admin con documento " + documento + " ya está registrado.");
	    }
	}

	private String generarCodigo() {
	    Random rand = new Random();
	    // Generar el código de 3 dígitos
	    int codigoNumerico = rand.nextInt(900) + 100; // Números entre 100 y 999
	    
	    // Generar las 3 letras aleatorias
	    StringBuilder codigoLetras = new StringBuilder();
	    for (int i = 0; i < 3; i++) {
	        char letra = (char) (rand.nextInt(26) + 'a'); // Letras minúsculas
	        codigoLetras.append(letra);
	    }
	    
	    // Combinar el código numérico y las letras
	    return String.format("%03d", codigoNumerico) + codigoLetras.toString();
	}
	
	// Método para agregar un nuevo producto al HashMap de productos
    public void agregarProductoNuevo(String nombre, String cantidad, String precio) {
    	
        String codigo = generarCodigo();
        
        Producto nuevoProducto = new Producto(Integer.valueOf(cantidad), codigo, nombre, Double.valueOf(precio));
        productos.put(codigo, nuevoProducto);
    }


	
	
    
    

}
