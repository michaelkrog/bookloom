/**
 * BaseResource is a base class that provides methods for interacting with a RESTful API.
 * It includes methods to find, save, and delete resources, and it automatically handles
 * authorization headers.
 */
class BaseResource {

    /**
     * Constructs a new BaseResource instance with the specified API path.
     * @param {string} path - The base path for the resource API (e.g., '/users', '/products').
     */
    constructor(path) {
        this.path = path;
    }

    /**
     * Retrieves all resources from the API.
     * @returns {Promise<Object[]>} - A promise that resolves to an array of all resources.
     * @throws {Error} - Throws an error if the request fails.
     */
    async findAll() {
        try {
            const headers = new Headers();
            this.applyAuthHeader(headers);
            const response = await fetch(`http://localhost:8080${this.path}`, { headers });
            return await response.json();
        } catch (error) {
            this.handleError(error);
        }
    }

    /**
     * Retrieves a single resource by its ID.
     * @param {string | number} id - The ID of the resource to retrieve.
     * @returns {Promise<Object>} - A promise that resolves to the resource object.
     * @throws {Error} - Throws an error if the request fails.
     */
    async findById(id) {
        try {
            const headers = new Headers();
            this.applyAuthHeader(headers);
            const response = await fetch(`http://localhost:8080${this.path}/${id}`, { headers });
            return await response.json();
        } catch (error) {
            this.handleError(error);
        }
    }

    /**
     * Saves a resource to the API. If the resource has an ID, it will update the existing resource.
     * Otherwise, it will create a new resource.
     * @param {Object} entity - The resource object to save.
     * @returns {Promise<Object>} - A promise that resolves to the saved resource object.
     * @throws {Error} - Throws an error if the request fails.
     */
    async save(entity) {
        try {
            const headers = new Headers();
            this.applyAuthHeader(headers);

            let response;
            if (entity.id != null) {
                // Update existing entity
                response = await fetch(`http://localhost:8080${this.path}/${entity.id}`, { headers, body: JSON.stringify(entity), method: 'PUT' });
            } else {
                // Create new entity
                response = await fetch(`http://localhost:8080${this.path}`, { headers, body: JSON.stringify(entity), method: 'POST' });
            }
            return await response.json();
        } catch (error) {
            this.handleError(error);
        }
    }

    /**
     * Deletes a resource from the API.
     * @param {Object} entity - The resource object to delete.
     * @returns {Promise<void>} - A promise that resolves when the resource is deleted.
     * @throws {Error} - Throws an error if the request fails.
     */
    async delete(entity) {
        try {
            const headers = new Headers();
            this.applyAuthHeader(headers);

            await fetch(`http://localhost:8080${this.path}/${entity.id}`, { headers, body: JSON.stringify(entity), method: 'DELETE' });
        } catch (error) {
            this.handleError(error);
        }
    }

    /**
     * Applies the authorization header to the provided Headers object.
     * The header is only applied if a token is found in localStorage.
     * @param {Headers} headers - The Headers object to which the authorization header will be added.
     */
    applyAuthHeader(headers) {
        const token = localStorage.getItem('token');
        if (token) {
            headers.set('Authorization', 'Bearer ' + token);
        }
    }

    handleError(error) {
        // TODO: Show toast notification to the user about the error.
        throw error;
    }
}

export default BaseResource;
