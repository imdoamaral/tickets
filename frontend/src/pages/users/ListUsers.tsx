import api from "@/services/api"
import type {UserInterface} from "@/types/users"
import {useEffect, useState} from "react"
import AppHeader from "@/components/AppHeader.tsx";


const ListUsers = () => {

    // Hook: useState
    const [users, setUsers] = useState<UserInterface[]>([])

    // Hook: useEffect
    useEffect(() => {

        api.get('/users')
            .then(response => {
                console.log('Response completo:', response)
                console.log('Response data:', response.data)
                console.log('Tipo de data:', typeof response.data)
                setUsers(response.data)
            })

    }, [])

    return (
        <>
            <AppHeader title={"Lista de usuários"}/>
            <ul>
                {
                    users.map(user => (
                        <li key={user.id}>{user.name}</li>
                    ))
                }
            </ul>
        </>
    )
}

export default ListUsers
