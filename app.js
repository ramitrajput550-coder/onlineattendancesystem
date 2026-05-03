// Base API URL
const API_BASE_URL = '/api';

// Check if user is logged in
function checkAuth(allowedRoles = []) {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        window.location.href = 'login.html';
        return null;
    }
    
    if (allowedRoles.length > 0 && !allowedRoles.includes(user.role)) {
        alert('Unauthorized access!');
        logout();
        return null;
    }
    
    return user;
}

// Logout function
function logout() {
    localStorage.removeItem('user');
    window.location.href = 'index.html';
}

// Setup common UI elements based on User
function setupUI(user) {
    const userInfo = document.getElementById('userInfo');
    if(userInfo) {
        userInfo.innerHTML = `Welcome, <b>${user.name}</b> (${user.role})`;
    }
}
