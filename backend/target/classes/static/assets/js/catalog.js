// Catalog page script
const API_BASE_URL = ""

let allProducts = []
let filteredProducts = []

// Declare showNotification function
function showNotification(message, type = "success") {
  // Implementation here
  console.log(`Notification (${type}): ${message}`)
}

// Load products from API
async function loadProducts() {
  try {
    const response = await fetch(`${API_BASE_URL}/products`)
    if (!response.ok) throw new Error("Error loading products")

    allProducts = await response.json()
    filteredProducts = [...allProducts]
    renderProducts()
  } catch (error) {
    console.error("Error:", error)
    document.getElementById("products-container").innerHTML =
      '<div class="col-12 text-center"><p class="text-danger">Error al cargar los productos</p></div>'
  }
}

// Render products
function renderProducts() {
  const container = document.getElementById("products-container")

  if (filteredProducts.length === 0) {
    container.innerHTML = '<div class="col-12 text-center"><p>No se encontraron productos</p></div>'
    return
  }

  container.innerHTML = filteredProducts
    .map(
      (product) => `
        <div class="col-md-4">
            <div class="product-card">
                <div class="position-relative">
                    <img src="${product.imageUrl || "/placeholder.svg?height=250&width=300"}"
                         alt="${product.name}" class="product-image">
                    <button class="wishlist-btn ${window.WishlistManager.isInWishlist(product.id) ? "active" : ""}"
                            onclick="toggleWishlist(event, ${product.id})">
                        <i class="fa${window.WishlistManager.isInWishlist(product.id) ? "s" : "r"} fa-heart"></i>
                    </button>
                </div>
                <div class="product-info">
                    <p class="product-category">${product.category?.name || "Sin categoría"}</p>
                    <h5 class="product-name">${product.name}</h5>
                    <p class="product-price">$${product.price.toFixed(2)}</p>
                    <button class="btn btn-accent w-100" onclick="addToCart(${product.id})">
                        <i class="fas fa-cart-plus me-2"></i>Añadir al Carrito
                    </button>
                </div>
            </div>
        </div>
    `,
    )
    .join("")
}

// Toggle wishlist
function toggleWishlist(event, productId) {
  event.stopPropagation()
  const product = allProducts.find((p) => p.id === productId)

  if (window.WishlistManager.isInWishlist(productId)) {
    window.WishlistManager.removeFromWishlist(productId)
    showNotification(`${product.name} eliminado de deseos`, "info")
  } else {
    window.WishlistManager.addToWishlist(product)
    showNotification(`${product.name} añadido a deseos`)
  }

  renderProducts()
}

// Add to cart
function addToCart(productId) {
  const product = allProducts.find((p) => p.id === productId)
  window.CartManager.addToCart(product)
}

// Filters
document.getElementById("searchInput")?.addEventListener("input", (e) => {
  const search = e.target.value.toLowerCase()
  filteredProducts = allProducts.filter(
    (p) => p.name.toLowerCase().includes(search) || p.description?.toLowerCase().includes(search),
  )
  applyFilters()
})

document.getElementById("categoryFilter")?.addEventListener("change", (e) => {
  const category = e.target.value
  if (category) {
    filteredProducts = allProducts.filter((p) => p.category?.name.toLowerCase() === category.toLowerCase())
  } else {
    filteredProducts = [...allProducts]
  }
  applyFilters()
})

document.getElementById("priceFilter")?.addEventListener("change", (e) => {
  const order = e.target.value
  if (order === "asc") {
    filteredProducts.sort((a, b) => a.price - b.price)
  } else if (order === "desc") {
    filteredProducts.sort((a, b) => b.price - a.price)
  }
  renderProducts()
})

function applyFilters() {
  const priceOrder = document.getElementById("priceFilter")?.value
  if (priceOrder === "asc") {
    filteredProducts.sort((a, b) => a.price - b.price)
  } else if (priceOrder === "desc") {
    filteredProducts.sort((a, b) => b.price - a.price)
  }
  renderProducts()
}

// Initialize
document.addEventListener("DOMContentLoaded", loadProducts)
