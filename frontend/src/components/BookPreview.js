import { Link } from "react-router-dom";

/**
 * BookPreview Component
 * 
 * This component displays a preview of a book, including its cover image, title, and price.
 * When clicked, it navigates to a detailed page for the book using React Router's `Link` component.
 * 
 * Props:
 * - `book` (object): The book object containing details to be displayed. 
 *    - `id` (string): The unique identifier for the book, used in the URL for the detailed page.
 *    - `title` (string): The title of the book.
 *    - `imageData` (string): Base64-encoded image data representing the book cover.
 *    - `price` (number): The price of the book in cents, which is converted to dollars and formatted as USD currency.
 * 
 * Usage:
 * ```jsx
 * const book = {
 *   id: '123',
 *   title: 'The Great Gatsby',
 *   imageData: 'iVBORw0KGgoAAAANSUhEUgAA...',
 *   price: 1500, // price in cents
 * };
 * 
 * <BookPreview book={book} />
 * ```
 * 
 * @component
 * @example
 * <BookPreview book={{ id: '123', title: 'The Great Gatsby', imageData: 'iVBORw0KGgoAAAANSUhEUgAA...', price: 1500 }} />
 */
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