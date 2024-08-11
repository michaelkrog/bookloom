import { Link } from "react-router-dom";

export default function BookDisplay({ book }) {

    return (
        <Link to={book.href} className="group">
            <div className="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg sm:aspect-h-3 sm:aspect-w-2">
                <img
                    alt={book.title}
                    src={book.imageSrc}
                    className="h-full w-full object-cover object-center group-hover:opacity-75"
                />
            </div>
            <div className="mt-4 flex items-center justify-between text-base font-medium text-gray-900">
                <h3>{book.title}</h3>
                <p>{book.price}</p>
            </div>
            <p className="mt-1 text-sm italic text-gray-500">{book.description}</p>
        </Link>
    )
}