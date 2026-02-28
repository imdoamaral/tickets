import api from "@/services/api"
import type { SaleInterface} from "@/types/sale.ts";
import { useEffect, useState } from "react"

const UpdateSaleStatus = () => {
    const [sales, setSales] = useState<SaleInterface[]>([])
    const [selectedSaleId, setSelectedSaleId] = useState('')
    const [newStatus, setNewStatus] = useState('PENDING')
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState('')

    useEffect(() => {
        api.get('/sales').then(res => setSales(res.data))
    }, [])

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)

        try {
            await api.put(`/sales/${selectedSaleId}`, {
                status: newStatus
            })
            setMessage('Status atualizado com sucesso!')
            setSelectedSaleId('')
            setNewStatus('PENDING')
            // Recarregar vendas
            api.get('/sales').then(res => setSales(res.data))
        } catch (error) {
            setMessage('Erro ao atualizar status')
        } finally {
            setLoading(false)
        }
    }

    return (
        <>
            <h2>Alterar Status da Venda</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Venda: </label>
                    <select value={selectedSaleId} onChange={(e) => setSelectedSaleId(e.target.value)} required>
                        <option value="">Selecione uma venda</option>
                        {sales.map(sale => (
                            <option key={sale.id} value={sale.id}>
                                {sale.eventName} - {sale.userName} ({sale.status})
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Novo Status: </label>
                    <select value={newStatus} onChange={(e) => setNewStatus(e.target.value)}>
                        <option value="PENDING">Pendente</option>
                        <option value="CONFIRMED">Confirmado</option>
                        <option value="CANCELLED">Cancelado</option>
                    </select>
                </div>
                <button type="submit" disabled={loading}>
                    {loading ? 'Atualizando...' : 'Atualizar Status'}
                </button>
            </form>
            {message && <p>{message}</p>}
        </>
    )
}

export default UpdateSaleStatus