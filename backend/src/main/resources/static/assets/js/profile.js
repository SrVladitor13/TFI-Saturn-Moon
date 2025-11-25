// Profile page script
const API_BASE_URL = '/api';

// Load user profile
async function loadProfile() {
    const user = AuthManager.currentUser;
    
    if (!user) {
        window.location.href = 'login.html';
        return;
    }
    
    // Fill form with current user data
    document.getElementById('firstName').value = user.firstName || '';
    document.getElementById('lastName').value = user.lastName || '';
    document.getElementById('email').value = user.email || '';
    document.getElementById('phone').value = user.phone || '';
}

// Handle profile update
document.getElementById('profileForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const user = AuthManager.currentUser;
    if (!user || !user.id) {
        showNotification('Error: Usuario no encontrado', 'danger');
        return;
    }
    
    const updateData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phone: document.getElementById('phone').value
    };
    
    try {
        const token = localStorage.getItem('authToken');
        const response = await fetch(`${API_BASE_URL}/users/${user.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(updateData)
        });
        
        if (!response.ok) throw new Error('Error al actualizar perfil');
        
        const updatedUser = await response.json();
        
        // Update local storage
        AuthManager.currentUser = { ...user, ...updatedUser };
        localStorage.setItem('currentUser', JSON.stringify(AuthManager.currentUser));
        
        showNotification('Perfil actualizado correctamente');
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error al actualizar el perfil', 'danger');
    }
});

// Initialize
document.addEventListener('DOMContentLoaded', loadProfile);
