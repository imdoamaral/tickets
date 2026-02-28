import {Link} from "react-router-dom"
import { navItems } from "@/config/navRoutes"

const Menu = () => {
    const cssMenu = "text-sm font-medium text-slate-600 transition-colors hover:text-indigo-600"

    return (
        <nav className="flex gap-4">
            {navItems.map(item => (
                <Link key={item.path} to={item.path} className={cssMenu}>
                    {item.label}
                </Link>
            ))}
        </nav>
    )
}

export default Menu