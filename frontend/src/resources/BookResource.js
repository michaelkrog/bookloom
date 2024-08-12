import BaseResource from './BaseResource';

class BookResource extends BaseResource{
  
  constructor() {
    super('http://localhost:8081/books');
  }

}

const resource = new BookResource();
export default resource;
