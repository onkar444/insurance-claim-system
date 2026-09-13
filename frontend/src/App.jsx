import { Dashboard } from "./pages/Dashboard";
import { LoginPage } from "./pages/LoginPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ProfilePage } from "./pages/ProfilePage";
import { PolicyList } from "./pages/PolicyList";
import { PolicyDetails } from "./pages/PolicyDetails";
import { ClaimsPage } from "./pages/ClaimsPage";
import { ClaimForm } from "./components/ClaimForm";
import { PolicyForm } from "./components/PolicyForm";
import { ProtectedRoute } from "./components/ProtectedRoute";
import './App.css';
import { SignIn } from "./pages/SignIn";
import { UserList } from "./pages/UserList";
import { AuthProvider, useAuth } from "./components/AuthContext";


function App() {

  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>

  )
}


function AppContent() {
  const { logout } = useAuth();

  return (
    <>
      <h1>Insurance Claim System</h1>
      <BrowserRouter>
        <Routes>

          <Route path="/login"
            element={
              <LoginPage />
            } />

          <Route path="/"
            element={
              <ProtectedRoute>
                <Dashboard handleLogout={logout} />
              </ProtectedRoute>
            } />

          <Route path="/profile" element={
            <ProtectedRoute>
              <ProfilePage />
            </ProtectedRoute>
          } />

          <Route path="/policies"
            element={
              <ProtectedRoute>
                <PolicyList />
              </ProtectedRoute>
            } />
          <Route path="/policy/:id"
            element={
              <ProtectedRoute>
                <PolicyDetails />
              </ProtectedRoute>
            } />

          <Route path="/policy/:id/claims"
            element={
              <ProtectedRoute >
                <ClaimsPage />
              </ProtectedRoute>
            } />

          <Route path="/policy/:id/submitclaim"
            element={
              <ProtectedRoute>
                <ClaimForm />
              </ProtectedRoute>
            } />

          <Route path="/policy/:id/update"
            element={
              <ProtectedRoute >
                <PolicyForm />
              </ProtectedRoute>
            } />

          <Route path="/user/all"
            element={
              <ProtectedRoute>
                <UserList />
              </ProtectedRoute>
            }>
          </Route>


          <Route
            path="/sign-in" element={<SignIn />} />
        </Routes>
      </BrowserRouter>
    </>

  )
}

export default App



