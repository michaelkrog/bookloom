import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import AdminShell from './shells/AdminShell';
import UserList from './pages/admin/UserList';
import UserEditor from './pages/admin/UserEditor';
import WebShell from './shells/WebShell';
import Home from './pages/Home';
import BookDetails from './pages/BookDetails';
import SignIn from './pages/SignIn';
import BookList from './pages/admin/BookList';

const App = () => (
  <Router>

    <Routes>
      <Route path="admin" element={<AdminShell />}>
          <Route path="users" element={<UserList />}></Route>
          <Route path="users/:id" element={<UserEditor />}></Route>
          <Route path="books" element={<BookList />}></Route>
      </Route>
      <Route path="" element={<WebShell />}>
        <Route path="" element={<Home />}></Route>
        <Route path="signin" element={<SignIn />}></Route>
        <Route path="books/:id" element={<BookDetails />}></Route>
      </Route>
    </Routes>
  </Router>
);

export default App;
