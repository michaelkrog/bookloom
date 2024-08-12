import { Link } from "react-router-dom";

export default function BookPreview({ book }) {

    return (
        <Link to={'books/' + book.id} className="group">
            <div className="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg border-2 sm:aspect-h-3 sm:aspect-w-2">
                <img
                    alt={book.title}
                    src={'data:image/png;base64,' + book.imageData}
                    className="h-full w-full object-cover object-center group-hover:opacity-75"
                />
            </div>
            <div className="mt-4 flex items-center justify-between text-base font-medium text-gray-900">
                <h3>{book.title}</h3>
                <p>{(book.price / 100).toLocaleString("en-US", {style:"currency", currency:"USD"})}</p>
            </div>
        </Link>
    )
}