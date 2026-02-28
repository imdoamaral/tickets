import api from "@/services/api"
import type { SaleInterface} from "@/types/sale.ts";
import { useEffect, useState } from "react"

const ListSales = () => {
    const [sales, setSales] = useState<SaleInterface[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        api.get('/sales')
            .then(response => {
                setSales(response.data)
                setLoading(false)
            })
            .catch(_err => {
                setError('Erro ao carregar vendas')
                setLoading(false)
            })
    }, [])

    if (loading) return <div>Carregando...</div>
    if (error) return <div>{error}</div>

    return (
        <>
            <h2>Lista de Vendas</h2>
            <table border={1}>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Evento</th>
                    <th>Usuário</th>
                    <th>Status</th>
                    <th>Data</th>
                </tr>
                </thead>
                <tbody>
                {sales.map(sale => (
                    <tr key={sale.id}>
                        <td>{sale.id}</td>
                        <td>{sale.eventName}</td>
                        <td>{sale.userName}</td>
                        <td>{sale.status}</td>
                        <td>{new Date(sale.createdAt).toLocaleDateString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </>
    )
}

export default ListSales