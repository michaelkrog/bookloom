// src/stores/DataStore.js
import { makeAutoObservable } from 'mobx';

class UserResource {
  username = 'user';
  password = 'qwerty';

  constructor() {
    makeAutoObservable(this);
  }

  async findAll() {
    this.isLoading = true;
    this.error = null;
    try {
      const headers = new Headers();
      headers.set('Authorization', 'Basic ' + btoa(this.username + ":" + this.password));
      const response = await fetch('http://localhost:8080/users', {headers});
      const result = await response.json();
      return result;
    } catch (error) {
      this.error = error;
    } finally {
      this.isLoading = false;
    }
  }
}

const dataStore = new UserResource();
export default dataStore;
