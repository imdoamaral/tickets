import { Outlet } from "react-router-dom";
import AppHeader from "@/components/AppHeader.tsx";

function App() {
	return (
		<>
			<AppHeader />
			<div className="p-4">
				<Outlet />
			</div>
		</>
	);
}

export default App;
