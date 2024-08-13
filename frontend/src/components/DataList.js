
/**
 * DataList Component
 * 
 * This component serves as a container for a list of data items. It styles the list
 * with a clean, minimalistic design using Tailwind CSS classes, making it suitable 
 * for displaying items like cards or rows with consistent spacing and borders.
 * 
 * Props:
 * - `children` (ReactNode): The list items to be rendered inside the `<ul>` element.
 *   Each child should typically be an `<li>` element or a component that renders an `<li>`.
 * 
 * Usage:
 * ```jsx
 * <DataList>
 *   <li>Item 1</li>
 *   <li>Item 2</li>
 *   <li>Item 3</li>
 * </DataList>
 * ```

 * @component
 * @example
 * <DataList>
 *   <li className="p-4">Item 1</li>
 *   <li className="p-4">Item 2</li>
 *   <li className="p-4">Item 3</li>
 * </DataList>
 */
export default function DataList({ children }) {

    return (
        <ul
            className="divide-y divide-gray-100 overflow-hidden bg-white shadow-sm ring-1 ring-gray-900/5 sm:rounded-xl"
        >
            {children}
        </ul>
    )
}