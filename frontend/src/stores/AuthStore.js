// authStore.js
import { makeAutoObservable } from 'mobx';
import { jwtDecode } from "jwt-decode";

/**
 * AuthStore Class
 * 
 * The `AuthStore` class manages authentication-related state, including the JWT token, username, roles, and token expiration. It uses MobX for state management and provides methods for setting and clearing authentication data.
 * 
 * The store maintains authentication information, handles token expiration, and provides utility methods to check if a user is authenticated and to log out.
 * 
 * @class
 */
class AuthStore {
  token = null;
  username = null;
  roles = [];
  expirationTimeout = null;

  constructor() {
    makeAutoObservable(this);

    // Load the token from localStorage if it exists
    const savedToken = localStorage.getItem('authToken');
    if (savedToken) {
      this.setToken(savedToken);
    }
  }

  /**
   * Set the JWT token, decode it, and schedule expiration.
   * @param {string} token - The JWT token.
   */
  setToken(token) {
    this.token = token;

    if (token) {
      const decoded = jwtDecode(token);
      this.username = decoded.sub; // Assuming `sub` contains the username
      this.roles = decoded.roles; // Assuming `roles` is an array of user roles

      // Persist token to localStorage
      localStorage.setItem('authToken', token);

      // Calculate expiration time and set a timeout to clear the token
      const expiresAt = decoded.exp * 1000; // Convert to milliseconds
      const currentTime = Date.now();
      const delay = expiresAt - currentTime;

      if (this.expirationTimeout) {
        clearTimeout(this.expirationTimeout);
      }

      if (delay > 0) {
        this.expirationTimeout = setTimeout(() => {
          this.logout();
        }, delay);
      } else {
        this.logout(); // If the token is already expired, log out immediately
      }
    } else {
      this.clearAuthData();
    }
  }

  /**
   * Clears the authentication data.
   */
  clearAuthData() {
    this.token = null;
    this.username = null;
    this.roles = [];
    localStorage.removeItem('authToken');
    if (this.expirationTimeout) {
      clearTimeout(this.expirationTimeout);
      this.expirationTimeout = null;
    }
  }

  /**
   * Logout the user by clearing the token and auth data.
   */
  logout() {
    this.clearAuthData();
  }

  /**
   * Check if the user is authenticated.
   * @return {boolean} True if authenticated, false otherwise.
   */
  get isAuthenticated() {
    return this.token !== null;
  }
}

// Create a single instance of the store and export it
const authStore = new AuthStore();
export default authStore;
