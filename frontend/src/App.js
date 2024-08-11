import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import AdminShell from './shells/AdminShell';
import UserListDisplay from './pages/UserListDisplay';
import UserEditor from './pages/UserEditor';
import WebShell from './shells/WebShell';
import Home from './pages/Home';

const App = () => (
  <Router>

    <Routes>
      <Route path="admin" element={<AdminShell />}>
          <Route path="users" element={<UserListDisplay />}></Route>
          <Route path="users/:id" element={<UserEditor />}></Route>
      </Route>
      <Route path="" element={<WebShell />}>
        <Route path="" element={<Home />}></Route>
      </Route>
    </Routes>
  </Router>
);

export default App;
