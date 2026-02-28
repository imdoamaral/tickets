import { createBrowserRouter } from 'react-router-dom'
import App from './App'
import ListUsers from './pages/users/ListUsers'
import CreateEvent from "@/pages/event/CreateEvent.tsx";
import ListSales from './pages/sales/ListSales'
import CreateSale from './pages/sales/CreateSale'
import UpdateSaleStatus from './pages/sales/UpdateSaleStatus'

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        children: [
            {
                path: '/users',
                element: <ListUsers />
            },
            {
                path: '/events/create',
                element: <CreateEvent />
            },
            {
                path: '/sales',
                element: <ListSales />
            },
            {
                path: '/sales/create',
                element: <CreateSale />
            },
            {
                path: '/sales/update-status',
                element: <UpdateSaleStatus />
            }
        ]
    }
])

export default router
