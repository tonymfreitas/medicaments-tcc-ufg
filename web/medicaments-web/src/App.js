import './App.css';

import { Route, Routes } from 'react-router-dom';
import Home from './routes/Home';
import ClassificationAtc from './routes/ClassificationAtc';

export default function App() {
  return (
    <Routes>
      <Route element={<Home />} path="/" exact />
      <Route element={<ClassificationAtc />} path="/classificationatc" />
    </Routes>
  );
}