import api from "@/services/api"
import type { EventInterface} from "@/types/event.ts";
import type { UserInterface } from "@/types/users"
import { useEffect, useState } from "react"

const CreateSale = () => {
    const [events, setEvents] = useState<EventInterface[]>([])
    const [users, setUsers] = useState<UserInterface[]>([])
    const [eventId, setEventId] = useState('')
    const [userId, setUserId] = useState('')
    const [status, setStatus] = useState('PENDING')
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')

    useEffect(() => {
        api.get('/events').then(res => setEvents(res.data))
        api.get('/users').then(res => setUsers(res.data))
    }, [])

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)

        try {
            await api.post('/sales', {
                eventId,
                userId,
                status
            })
            setMessage('Venda criada com sucesso!')
            setEventId('')
            setUserId('')
            setStatus('PENDING')
        } catch (error) {
            setMessage('Erro ao criar venda')
        } finally {
            setLoading(false)
        }
    }

    return (
        <>
            <h2>Cadastrar Venda de Ingresso</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Evento: </label>
                    <select value={eventId} onChange={(e) => setEventId(e.target.value)} required>
                        <option value="">Selecione um evento</option>
                        {events.map(event => (
                            <option key={event.id} value={event.id}>{event.name}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Usuário: </label>
                    <select value={userId} onChange={(e) => setUserId(e.target.value)} required>
                        <option value="">Selecione um usuário</option>
                        {users.map(user => (
                            <option key={user.id} value={user.id}>{user.name}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Status: </label>
                    <select value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="PENDING">Pendente</option>
                        <option value="CONFIRMED">Confirmado</option>
                        <option value="CANCELLED">Cancelado</option>
                    </select>
                </div>
                <button type="submit" disabled={loading}>
                    {loading ? 'Criando...' : 'Criar Venda'}
                </button>
            </form>
            {message && <p>{message}</p>}
        </>
    )
}

export default CreateSale