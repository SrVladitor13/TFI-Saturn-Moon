// Sistema de autenticación simple (localStorage)
const AuthManager = {
  currentUser: null,

  init() {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser")) || null
    this.updateUIBasedOnAuth()
    this.setupEventListeners()
  },

  updateUIBasedOnAuth() {
    const loginLink = document.getElementById("login-link")
    const profileLink = document.getElementById("profile-link")
    const ordersLink = document.getElementById("orders-link")
    const adminLink = document.getElementById("admin-link")
    const logoutLink = document.getElementById("logout-link")

    if (this.currentUser) {
      loginLink.style.display = "none"
      profileLink.style.display = "block"
      ordersLink.style.display = "block"
      logoutLink.style.display = "block"

      if (this.currentUser.role === "admin") {
        adminLink.style.display = "block"
      }
    } else {
      loginLink.style.display = "block"
      profileLink.style.display = "none"
      ordersLink.style.display = "none"
      adminLink.style.display = "none"
      logoutLink.style.display = "none"
    }
  },

  setupEventListeners() {
    const logoutLink = document.getElementById("logout-link")
    if (logoutLink) {
      logoutLink.addEventListener("click", (e) => {
        e.preventDefault()
        this.logout()
      })
    }
  },

  logout() {
    localStorage.removeItem("currentUser")
    this.currentUser = null
    this.updateUIBasedOnAuth()
    window.location.href = "index.html"
  },
}

// Gestor del carrito
const CartManager = {
  cart: [],

  init() {
    this.cart = JSON.parse(localStorage.getItem("cart")) || []
    this.updateCartCount()
  },

  addToCart(product) {
    const existingItem = this.cart.find((item) => item.id === product.id)

    if (existingItem) {
      existingItem.quantity++
    } else {
      this.cart.push({ ...product, quantity: 1 })
    }

    this.saveCart()
    this.updateCartCount()
    this.showNotification(`${product.name} añadido al carrito`)
  },

  removeFromCart(productId) {
    this.cart = this.cart.filter((item) => item.id !== productId)
    this.saveCart()
    this.updateCartCount()
  },

  updateQuantity(productId, quantity) {
    const item = this.cart.find((item) => item.id === productId)
    if (item) {
      item.quantity = Math.max(1, quantity)
      this.saveCart()
      this.updateCartCount()
    }
  },

  saveCart() {
    localStorage.setItem("cart", JSON.stringify(this.cart))
  },

  updateCartCount() {
    const count = this.cart.reduce((total, item) => total + item.quantity, 0)
    const cartCountElement = document.getElementById("cart-count")
    if (cartCountElement) {
      cartCountElement.textContent = count
    }
  },

  getTotal() {
    return this.cart.reduce((total, item) => total + item.price * item.quantity, 0)
  },

  clear() {
    this.cart = []
    this.saveCart()
    this.updateCartCount()
  },
}

// Gestor de lista de deseos
const WishlistManager = {
  wishlist: [],

  init() {
    this.wishlist = JSON.parse(localStorage.getItem("wishlist")) || []
  },

  addToWishlist(product) {
    if (!this.wishlist.find((item) => item.id === product.id)) {
      this.wishlist.push(product)
      this.saveWishlist()
      AuthManager.currentUser && AuthManager.currentUser.role !== "admin"
        ? showNotification(`${product.name} añadido a deseos`)
        : null
    }
  },

  removeFromWishlist(productId) {
    this.wishlist = this.wishlist.filter((item) => item.id !== productId)
    this.saveWishlist()
  },

  saveWishlist() {
    localStorage.setItem("wishlist", JSON.stringify(this.wishlist))
  },

  isInWishlist(productId) {
    return this.wishlist.some((item) => item.id === productId)
  },
}

// Notificaciones
function showNotification(message, type = "success") {
  const alertDiv = document.createElement("div")
  alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed`
  alertDiv.style.top = "80px"
  alertDiv.style.right = "20px"
  alertDiv.style.zIndex = "9999"
  alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `

  document.body.appendChild(alertDiv)

  setTimeout(() => {
    alertDiv.remove()
  }, 3000)
}

// Inicialización
document.addEventListener("DOMContentLoaded", () => {
  AuthManager.init()
  CartManager.init()
  WishlistManager.init()
})
