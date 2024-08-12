import authStore from "../stores/AuthStore";

/**
 * BaseResource is a base class that provides methods for interacting with a RESTful API.
 * It includes methods to find, save, and delete resources, and it automatically handles
 * authorization headers.
 */
class BaseResource {

    /**
     * Constructs a new BaseResource instance with the specified API path.
     * @param {string} endpointUrl - The endpointUrl for the resource API (e.g., 'http://localhost:8080/users', 'http://localhost:8081/books').
     */
    constructor(endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    /**
     * Retrieves all resources from the API.
     * @returns {Promise<Object[]>} - A promise that resolves to an array of all resources.
     * @throws {Error} - Throws an error if the request fails.
     */
    async findAll(pageable) {
        try {
            const headers = new Headers();
            this.applyAuthHeader(headers);
            let url = this.endpointUrl + this.generateQueryString(pageable);

            const response = await fetch(url, { headers });
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
            const response = await fetch(`${this.endpointUrl}/${id}`, { headers });
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
                response = await fetch(`${this.endpointUrl}/${entity.id}`, { headers, body: JSON.stringify(entity), method: 'PUT' });
            } else {
                // Create new entity
                response = await fetch(`${this.endpointUrl}`, { headers, body: JSON.stringify(entity), method: 'POST' });
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

            await fetch(`${this.endpointUrl}/${entity.id}`, { headers, body: JSON.stringify(entity), method: 'DELETE' });
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
        const authenticated = authStore.isAuthenticated;
        if (authenticated) {
            headers.set('Authorization', 'Bearer ' + authStore.token);
        }
    }

    handleError(error) {
        console.error(error);
        // TODO: Show toast notification to the user about the error.
        throw error;
    }

    /**
     * Generates a query string from the given pageable object.
     * 
     * This function takes a pageable object, which may contain the properties 
     * `page`, `size`, `sort`, and `filter`. It generates a URL query string based on the 
     * properties that are defined. If the pageable object is undefined or empty, 
     * an empty string is returned.
     * 
     * @param {Object} pageable - The pageable object containing pagination parameters and filter.
     * @param {number} [pageable.page] - The current page number (optional).
     * @param {number} [pageable.size] - The size of the page (optional).
     * @param {string} [pageable.sort] - The sorting criteria (optional).
     * @param {string} [pageable.filter] - The filter criteria (optional).
     * 
     * @returns {string} - A URL query string based on the defined properties of pageable. 
     * If no properties are defined, an empty string is returned.
     */
    generateQueryString(pageable) {
        if (!pageable) {
            return '';
        }

        const params = [];

        if (pageable.page !== undefined) {
            params.push(`page=${encodeURIComponent(pageable.page)}`);
        }

        if (pageable.size !== undefined) {
            params.push(`size=${encodeURIComponent(pageable.size)}`);
        }

        if (pageable.sort !== undefined) {
            params.push(`sort=${encodeURIComponent(pageable.sort)}`);
        }

        if (pageable.filter !== undefined) {
            Object.keys(pageable.filter).forEach(key => {
                const value = pageable.filter[key];
                if(value === undefined) return;

                if(value instanceof Array) {
                    value.forEach(e => params.push(`${key}=${encodeURIComponent(e)}`));
                } else {
                    params.push(`${key}=${encodeURIComponent(value)}`);
                }
            });
        }

        return params.length > 0 ? `?${params.join('&')}` : '';
    }
}

export default BaseResource;
