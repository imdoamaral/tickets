import AppHeader from "@/components/AppHeader.tsx";
import { Outlet } from "react-router-dom";

function App() {

  return <>
      <AppHeader/>
      <div className="p-4">
        <Outlet />
      </div>
  </>
}

export default App
