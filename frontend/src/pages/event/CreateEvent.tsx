import api from "@/services/api"
import { useState } from "react"

const CreateEvent = () => {
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [date, setDate] = useState('')
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)

        try {
            await api.post('/events', {
                name,
                description,
                date
            })
            setMessage('Evento criado com sucesso!')
            setName('')
            setDescription('')
            setDate('')
        } catch (error) {
            setMessage('Erro ao criar evento')
        } finally {
            setLoading(false)
        }
    }

    return (
        <>
            <h2>Cadastrar Evento</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nome: </label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Descrição: </label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Data: </label>
                    <input
                        type="date"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" disabled={loading}>
                    {loading ? 'Criando...' : 'Criar Evento'}
                </button>
            </form>
            {message && <p>{message}</p>}
        </>
    )
}

export default CreateEvent