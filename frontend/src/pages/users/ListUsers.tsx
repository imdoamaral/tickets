import { useEffect, useState } from "react";
import Card from "@/components/Card.tsx";
import api from "@/services/api";
import type { UserInterface } from "@/types/users";

const ListUsers = () => {
	// Hook: useState
	const [users, setUsers] = useState<UserInterface[]>([]);

	// Hook: useEffect
	useEffect(() => {
		api.get("/users").then((response) => {
			console.log("Response completo:", response);
			console.log("Response data:", response.data);
			console.log("Tipo de data:", typeof response.data);
			setUsers(response.data);
		});
	}, []);

	return (
		<div className="flex flex-wrap justify-center">
			{users.map((user) => (
				<Card
					key={user.id}
					id={user.id}
					name={user.name}
					updateUrl={`/users/${user.id}`}
					deleteUrl={`/users/${user.id}/delete`}
				/>
			))}
		</div>
	);
};

export default ListUsers;
