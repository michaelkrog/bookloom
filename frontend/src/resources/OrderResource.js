import BaseResource from './BaseResource';

class OrderResource extends BaseResource {
  
  constructor() {
    super('/orders');
  }

  // Add special method for approving order etc.
}

const resource = new OrderResource();
export default resource;
