import BaseResource from './BaseResource';

class UserResource extends BaseResource {

  constructor() {
    super('/users');
  }

  /**
   * Authenticates a user with the given email and password.
   * 
   * @async
   * @param {string} email - The user's email address.
   * @param {string} password - The user's password.
   * @throws Will call `handleError` if the authentication request fails.
   * 
   * @description
   * This method creates a Basic Authentication token using the provided email and password,
   * then sends a POST request to the authentication endpoint. If the request is successful,
   * the token returned by the server is stored in `localStorage` under the key 'token'.
   */
  async authenticate(email, password) {
    try {
      // Combine email and password to form the Basic Authentication token.
      const token = `${email}:${password}`;

      // Create a Headers object and set the Authorization header with the encoded token.
      const headers = new Headers();
      headers.set('Authorization', `Basic ${btoa(token)}`);

      // Send a POST request to the authentication endpoint with the headers.
      const response = await fetch(`http://localhost:8080${this.path}/actions/authenticate`, {
        headers,
        method: 'POST'
      });

      // Parse the JSON response and store the token in localStorage.
      const result = await response.json();
      localStorage.setItem('token', result.token);
    } catch (error) {
      // Handle any errors that occur during the request.
      this.handleError(error);
    }
  }

}

const resource = new UserResource();
export default resource;
