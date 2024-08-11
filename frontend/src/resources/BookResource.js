import BaseResource from './BaseResource';

class BookResource extends BaseResource{
  
  constructor() {
    super('/books');
  }

}

const resource = new BookResource();
export default resource;
